import java.util.List;

public class TTEmployee {
  // attributes
  int id;
  int age;
  String name;
  List<String> skills;

  // constructors
  public TTEmployee(int id, int age, String name, List<String> skills) {
    this.id = id;
    this.age = age;
    this.name = name;
    this.skills = skills;
  }

  // methods
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getSkills() {
    return skills;
  }

  public void setSkills(List<String> skills) {
    this.skills = skills;
  }
}
