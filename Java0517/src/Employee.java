import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//1. make the class final -> prevent from being inherited
//2. make the attributes private and final -> prevent them from being changed
//privare -> code could not be changed from exteral
//fianl -> code can only be assinged once
//3. make the methods finail -> prevent from override
//4. getter but no setter
// mutable -> deep copy

/**         resizable array -> bucket
 *          bucket head
 *          hashcode method -> hashvalue mod (size of array)
 *
 *          PLUS
 *
 *          a group of LinkedList
 *
 *         |----------|----------|----------|----------|----------|----------|----------|----------|
 *         |  value1  |  value2  |  value3  |  value4  |  value5  |  value6  |  value7  |  value8  |        |          |          |          |          |          |          |          |
 *         |          |          |          |          |          |          |          |          |
 *         ------\-----------------------------------------------------------------------------------
 *                \
 *        |------\----|
 *        |  value9  |
 *        |          |
 *        ------------
 *        ------\-----
 *              \
 *        |------\----|
 *        |  value10  |
 *        |          |
 *       ------------
 *       ------\-----
 *             \
 *      |------\----|
 *      |  value11  |
 *      |          |
 *      ------------
 *          /
 **/
public final class Employee {

  private final String name;
  private final int age;
  private final ArrayList<String> skills; // mutable

  public Employee(String name, int age, ArrayList<String> skills) {
    this.name = name;
    this.age = age;
    this.skills = new ArrayList<>(skills); // mutable
  }

  // getter
  public final String getName() {
    return name;
  }

  public final int getAge() {
    return age;
  }

  public final ArrayList<String> getSkills() {
    return new ArrayList<>(skills); // mutable
  }

  // hashset vs hashmap

  // hash collision
  // --> two objects have the same hashcodes  ----> wrong
  // --> two DIFFERENT objects have the same hashcodes  ----> Correct

  // hashmap
  // -> key1-value1 -insertion 1
  // -> key1-value2 -insertion 2-1

  // -> key1-value1 -insertion 1
  // -> key2-value1 -insertion 2-2

  //  key1 -> hahsvalue1
  //  key2 -> hahsvalue1

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Employee employee = (Employee) o;
    return age == employee.age && Objects.equals(name, employee.name)
        && Objects.equals(skills, employee.skills);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, skills);
  }

  public static void main(String[] args) {
//    String s = "A";
//    String s1 = new String("A");
//    String s2 = "A";
//    System.out.println(s == s1);
//    System.out.println(s == s2);
    // StringBuilder vs StringBuffer --> String immutable  -> mutable string ?
//     Integer i1 = new Integer(1);
//     Integer i2 = new Integer(1);
//     System.out.println(i1 == i2);
    // expect output: false;
    // output: true
    // in jdk8:
    // Integer -> static group numbers -> (0-255) -> new Integer(1
//    Integer i3 = Integer.valueOf(1);

    // original skill list
    ArrayList<String> originalSkills = new ArrayList<>();
    originalSkills.add("Java");
    originalSkills.add("SQL");

    // create a new employee with the original skill list
    Employee emp = new Employee("Alice", 30, originalSkills);
    Employee emp2 = new Employee("Alice", 30, originalSkills);

    // try to modify the original skill list
    originalSkills.add("Python");

    // print and test
    System.out.println("Employee skills (after adding python): " + emp.getSkills()); // [Java, SQL]

    // try to modify the getter result
    ArrayList<String> retrievedSkills = emp.getSkills();
    retrievedSkills.add("C++");

    // print and test
    System.out.println("Employee skills (after trying to modify getter result): " + emp.getSkills()); // [Java, SQL]


    // compare the test
    Set<Employee> set = new HashSet<>();
    set.add(emp);
    set.add(emp2);
    System.out.println(set.size());
    // before override: 2 -> check Obj class's equals() method -> return "==" compare the memory address
    // after override: 1 -> both hashcode() and equals()


  }

}





