import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// When to use Callable?
// 1. There is a return value
// 2. An exception may be thrown
// 3. Tasks that take a while to complete (such as API requests, I/O operations)
// 4. Future is a must for Callable ->
public class MyCallable implements Callable<String> {

  @Override
  public String call() throws Exception {
    return "This is Callable with thread ID：" + Thread.currentThread().getId();
  }

  // fix .get() -> either us throws Exception or try catch Exception
  public static void main(String[] args) throws Exception{

    // create callable task using MyCallable class
    MyCallable task1 = new MyCallable();

    // Create a Callable task using lambda and capture external variables
    int a = 10;
    int b = 5;
    Callable<Integer> task2 = () -> a + b;

    // Print the ID of the main thread
    System.out.println("Main thread ID: " + Thread.currentThread().getId());

    // Create a thread pool with 2 threads
    ExecutorService executor = Executors.newFixedThreadPool(2);

    // Submit tasks -> task1 & task2 to the thread pool
    Future<String> future1 = executor.submit(task1);
    Future<Integer> future2 = executor.submit(task2);

    System.out.println("waiting for results");

    // Retrieve the results from the threads
    String result1 = future1.get();    // Wait for task1 to complete and get result
    Integer result2 = future2.get();   // Wait for task2 to complete and get result

    // Print results
    System.out.println("Result 1: " + result1);
    System.out.println("Result 2: " + result2);

    // Shutdown the thread pool
    executor.shutdown();
  }
}

//Callable<T>: can return a result (T) and throw an exception.
//submit()： Execution of Callable task
//Future.get(): Wait and get the return value (may be blocked)


