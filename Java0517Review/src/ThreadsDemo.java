// concurrency vs in parallel
// # of CPU

// 1CPU: 1 core 1 thread ---> ???
// ipod -> embedded system SoC -> playing a sound -> 3minutes -> hit play -> thread -> play music
// play/stop/volume adjust -> physical buttons: interruption signals ->

// (not just java) difference in memory -> process not share memory / thread can share memory
//  volatile keyword in java
//What is Thread? -> The smallest execution unit in a program (Process).
//A program (Process) can have multiple Threads working at the same time.
//Eg.: An App downloads images and displays UI simultaneously.

// Thread vs Runnable vs Callable
//  class vs interface vs interface
// callable: response, runnable: no response
// callable: exception, runnable: no exception







// Thread vs Process:
// 1. Thread: A single execution unit in a program. / Share the same MM space with other threads.
// 2. Process: A collection of threads executing in parallel. / Independent MM space

//Thread Pool: Reuse a fixed number of threads to handle tasks, avoiding repeated creation and destruction.
// Eg. ExecutorService executor = Executors.newFixedThreadPool(5);

// sleep(ms): Thread suspends execution for a period of time (without releasing the lock) || Thread category
// wait(): Wait and release the lock until other Thread calls notify() || Object Class
// notify(): wake up a waiting Thread || Object class
// notifyAll(): wake up all waiting Thread || Object categories

// Thread Safety: synchronized, Lock
/**
  synchronized (this) {
      block of code
}


Lock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
    } finally {
    lock.unlock();
}
 **/

//CAS (Compare-And-Swap) and voatile keyword -> ??

public class ThreadsDemo {
  public static void main(String[] args) {
    System.out.println("Hello and welcome!");
  }

}
