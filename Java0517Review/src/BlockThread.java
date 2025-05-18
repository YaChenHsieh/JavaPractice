public class BlockThread {
  public static void main(String[] args) {
    Object lock = new Object();

    Thread t1 = new Thread(() -> {
      synchronized (lock) {
        System.out.println("t1 acquired the lock and will sleep");
        try {
          Thread.sleep(3000); // Holds the lock while sleeping (lock is NOT released)
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (lock) {
        System.out.println("t2 successfully acquired the lock (this line will block until t1 releases it)");
      }
    });

    t1.start();
    try {
      Thread.sleep(100); // Ensure t1 starts first and acquires the lock
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t2.start(); // t2 will enter BLOCKED state until t1 releases the lock
  }
}
