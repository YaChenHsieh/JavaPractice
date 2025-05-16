import java.util.ArrayList;
import java.util.List;


// immutable class
public final class Employee {


  private final String lastName;
  private final String firstName;
  private final String email;
  private final Integer password;
  private final Boolean flagged;
  private final List<Integer> list;




  public Employee(String lastName, String firstName, String email, Integer password,
      Boolean flagged,
      List<Integer> list) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.email = email;
    this.password = password;
    this.flagged = flagged;
    this.list = new ArrayList<>(list);
  }


  // getter
  public String getLastName() {
    return lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getEmail() {
    return email;
  }

  public Integer getPassword() {
    return password;
  }

  public Boolean getFlagged() {
    return flagged;
  }

  public List<Integer> getList() {
    return new ArrayList<>(list);
  }


  @Override
  public String toString() {
    return "Employee{" +
        "list=" + list +
        '}';
  }


  public static void main(String[] args) {


    List<Integer> scores = new ArrayList<>();
    scores.add(80);
    Employee e = new Employee("Lin", "Angel", "angel@example.com", 1234, true, scores);


    // change origin list does not influence Employee list
    scores.add(100);
    System.out.println(e.getList()); // still [80] not [80, 100]


    // try changing inner list
    e.getList().add(999); // change the copy not origin
    System.out.println(e.getList()); // [80]
  }
}

