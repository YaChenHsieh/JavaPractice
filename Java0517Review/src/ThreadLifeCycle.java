public class ThreadLifeCycle {
  public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      try {
        System.out.println("2. Runnable");   // The thread enters the Runnable state
        Thread.sleep(1000);                  // Simulate the thread running (Running state)
        System.out.println("3. Running");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    System.out.println("1. New");    // Thread object created (New state)
    thread.start();                  // Start the thread → enters Runnable state

    try {
      thread.join();               // Wait for the thread to finish (i.e., reach Terminated state)
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("5. Terminated"); // Thread has finished execution (Terminated state)
  }
}
// 1. New:       Thread object created but not started yet.
// 2. Runnable :  Thread is ready and waiting for CPU.
// 3. Running  :  Thread is currently executing.
// 5. Terminated: Thread has completed execution.

//Blocked / Waiting – waiting for other Threads to release resource



