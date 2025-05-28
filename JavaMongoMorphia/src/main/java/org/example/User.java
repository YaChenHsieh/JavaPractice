package org.example;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;

@Entity("users") // MongoDB collection 名稱
public class User {
  @Id
  private String id;

  private String name;
  private int age;

  public User() {}

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  // Getter & Setter
  public String getId() { return id; }
  public void setId(String id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public int getAge() { return age; }
  public void setAge(int age) { this.age = age; }

  @Override
  public String toString() {
    return "User{id='%s', name='%s', age=%d}".formatted(id, name, age);
  }
}
