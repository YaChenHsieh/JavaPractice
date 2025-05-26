import java.util.*;
import java.util.stream.*;

public class MinSalary {
  public static void main(String[] args) {
    List<JavaEmployee> javaemployees = Arrays.asList(
        new JavaEmployee("Alice", 50000),
        new JavaEmployee("Bob", 60000),
        new JavaEmployee("Charlie", 40000)
    );
    Optional<JavaEmployee> minEmployee = javaemployees.stream()
        .min(Comparator.comparingInt(JavaEmployee::getSalary));

    if (minEmployee.isPresent()) {
      System.out.println(": " + minEmployee.get().getName() +
          "，salary is: " + minEmployee.get().getSalary());
    } else {
      System.out.println("No data");
    }
  }
}
