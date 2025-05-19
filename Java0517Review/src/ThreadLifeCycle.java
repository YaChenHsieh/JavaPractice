public class ThreadLifeCycle {

  public static void main(String[] args) {
    Thread mainThread = Thread.currentThread();
    logTheadInfo("main thread starting", mainThread);
    Thread thread = new Thread(() -> {
      try {
        logTheadInfo("runnable thread starting", Thread.currentThread());
        System.out.println("2. Runnable");   // The thread enters the Runnable state
        Thread.sleep(1000);                  // Simulate the thread running (Running state)
        logTheadInfo("fnished sleeping", Thread.currentThread());
        System.out.println("3. Running");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "worker Thread");

    System.out.println("1. New");    // Thread object created (New state)
//    //
    thread.start();                  // Start the thread → enters Runnable state
    thread.start();                  // Start the thread → enters Runnable state

//    thread.run();
//    thread.run();

    try {
      thread.join();               // Wait for the thread to finish (i.e., reach Terminated state)
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("5. Terminated"); // Thread has finished execution (Terminated state)
  }

  private static void logTheadInfo(String message, Thread thread) {
    System.out.printf("[%tT.%<tL] %-20s - Thread ID: %d, Name: %s, State: %s, Priority: %d%n",
        new java.util.Date(),
        message,
        thread.getId(),
        thread.getName(),
        thread.getState(),
        thread.getPriority());
  }
}

// 1. New:       Thread object created but not started yet.
// 2. Runnable :  Thread is ready and waiting for CPU.
// 3. Running  :  Thread is currently executing.
// 5. Terminated: Thread has completed execution.

//Blocked / Waiting – waiting for other Threads to release resource



