// Runnable and Callable are both task-defining interfaces in Java's concurrency model.
// Runnable is simpler and doesn't return a result. (Use Thread t and ExecutorService)
// Callable returns a value and allows exception handling, but Callable requires execution via ExecutorService(threads pool) and returns a Future.

import java.util.concurrent.*;

public class MyRunnable implements Runnable{

  @Override
  public void run() {
    System.out.println("Single thread without returning value!");
    System.out.println("Current running thread ID: " + Thread.currentThread().getId());
  }

  public static void main(String[] args) {

    // 👉（Main thread）
    System.out.println("Main thread ID: " + Thread.currentThread().getId());

    // 👉 Manually Thread #1：use MyRunnable class
    MyRunnable task = new MyRunnable();
    Thread t1 = new Thread(task); // ⬅️ create thread1
    t1.start();

    // 👉 Manually Thread #2：(use Runnable with lambda)
    Runnable task1 = () -> System.out.println("Runnable with executor");
    Thread t2 = new Thread(task1); // ⬅️ create thread2
    t2.start();

    // 👉 Create Thread pool（using Runnable task1）
    ExecutorService executor = Executors.newFixedThreadPool(1); // ⬅️ create thread pool（1 thread）
    executor.submit(task1); // ⬅️ exec task1
    executor.shutdown();
  }
  // Totally 4 threads: Main thread, Thread #1, Thread #2, Thread pool (1 thread)
  //  ExecutorService executor = Executors.newFixedThreadPool(3);   // fixed thread -> 3 threads
  //  ExecutorService executor = Executors.newSingleThreadExecutor(); // only 1
  //  ExecutorService executor = Executors.newCachedThreadPool();   // dynamically create/release
  //

}
