import java.util.List;

public class EmployeeSingleton {

  private final String lastName;
  private final String firstName;
  private final String email;
  private final Integer password;
  private final Boolean flagged;
  private final List<Integer> list;

  // 1. init the obj first，then always return this only obj -> singleton
  private static EmployeeSingleton instance = new EmployeeSingleton();

  // 2. private constructor -> other obj can not use new to get a new instance
  private EmployeeSingleton(){}

  // 3. since constructor is private，need to provide method to let other call this class

  public static EmployeeSingleton getInstance(){

//  // 5. multi-threads use synchronized to make sure Singleton is the only
//  public static synchronized EmployeeSingleton getInstance(){

    // 4. If creating an instance costs time, we want it only init when it first called
    // The first if: avoid unnecessary locking
    //  ->if there is already an instance, don't waste performance entering the synchronized block
    // The second if (inside the lock): ensures that only one thread will create the instance
    //   -> when multiple threads enter at the same time, only the first one will create the object, and the others will be ignored
    if(instance == null ) {
      // if only use synchronized, it might reduce the efficiency -> use Double-Checked Locking
      synchronized (EmployeeSingleton.class){
        if(instance == null){
          instance = new EmployeeSingleton();
        }
      }
    }

    return instance;
  }

}
