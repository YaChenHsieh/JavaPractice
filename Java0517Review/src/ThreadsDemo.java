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

// ThreadPoolExecutor Process
//Step1:判斷是否可以使用核心執行緒
//* 如果目前執行中的執行緒數量 < corePoolSize:
//    → 直接建立新執行緒，立刻執行任務
//
//Step2:若核心執行緒都在忙，則嘗試將任務放進 queue(任務佇列)
//* 如果佇列(workQueue)還有空間:
//    → 任務會被排隊，等有空閒執行緒時再來執行
//
//Step3:如果佇列也滿了，才會開始創建額外的非核心執行緒
//* 如果目前執行緒數量 < maximumPoolSize:
//    → 創建新的 thread 來處理任務(屬於非核心執行緒)
//
//Step4:如果執行緒數已達上限、佇列也滿了
//* 任務無法被處理時，就會啟動 拒絕策略(RejectedExecutionHandler)
//
//Handler:
//AbortPolicy(預設):丟出例外(RejectedExecutionException)
//CallerRunsPolicy:由提交任務的主線程自己執行該任務
//DiscardPolicy:直接丟掉這個任務，不做任何處理
//DiscardOldestPolicy:丟掉 queue 裡最舊的任務，把新的任務塞進去



//                任務進來
//                     ↓
//    [1] 還沒達 corePoolSize？
//    → 建 thread 直接跑
//                     ↓
//    [2] 達了 corePoolSize，那 queue 還有空？
//    → 丟進去 queue
//                     ↓
//    [3] queue 也滿了，那還能加 thread？
//    → 再建 thread 跑（到 maxPoolSize 為止）
//                     ↓
//    [4] thread、queue 都滿了
//    → 啟用 handler 拒絕策略


