// A Singleton ensures that only one instance of a class exists in the application.
// It's commonly used for shared resources like loggers, configuration files, or database connections.
// This pattern provides a global access point, ensures thread-safe consistency, and saves memory by avoiding redundant object creation.

// single thread -> multi-threads(safe)
public class SingletonReview {

  // private static instance; marked volatile to prevent instruction reordering and ensure visibility across threads
  private volatile static SingletonReview instance;

  // private constructor to prevent external instantiation
  private SingletonReview() {}

  // method -> using Double-Checked Locking Singleton to prevent race condition and reduce sychronized time
  public static SingletonReview getInstance() {
    if (instance == null) { // First check: skip synchronization when instance already exists
      synchronized ( SingletonReview.class){
        if (instance == null){ // Second check: ensure only one instance is created in a multithreaded environment
          instance = new SingletonReview(); // instance creation is Not an atomic operation; volatile ensures proper construction visibility
        }
      }
    }
  return instance;
  }
}
