package com.example.jsp.model;

public class User {
  private int id;
  private String name;
  private String email;
  private Integer age;

  // Constructor without para（Servlet use）
  public User() {}

  // Constructor with para（easy to init）
  public User(int id, String name, String email, Integer age) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.age = age;
  }

  // Getter & Setter
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public Integer getAge() {
    return age;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setAge(Integer age) {
    this.age = age;
  }
}
