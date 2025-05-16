import java.lang.reflect.Array;
import java.util.ArrayList;

//1. make the class final -> prevent from being inherited
//2. make the attributes private and final -> prevent them from being changed
//privare -> code could not be changed from exteral
//fianl -> code can only be assinged once
//3. make the methods finail -> prevent from override
//4. getter but no setter
// mutable -> deep copy

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
}
