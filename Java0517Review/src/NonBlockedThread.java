public class NonBlockedThread {
  public static void main(String[] args) {
    Object lock = new Object();

    Thread t1 = new Thread(() -> {
      synchronized (lock) {
        System.out.println("t1 acquired the lock and finished quickly");
      }
      try {
        Thread.sleep(3000); // Sleep AFTER releasing the lock
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread t2 = new Thread(() -> {
      synchronized (lock) {
        System.out.println("t2 acquired the lock without being blocked");
      }
    });

    t1.start();
    try {
      Thread.sleep(100); // Give t1 a head start
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    t2.start(); // t2 will not be blocked because t1 released the lock early
  }
}
