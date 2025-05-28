package org.example.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users") // 對應到 DB 中的表格
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動編號（對應 SERIAL）
  private int id;

  @Column(nullable = false, unique = false, length = 100)
  // nullable = false -> 不可為 NULL
  // unique = false -> 欄位值可重複
  // length = 100 -> 欄位長度限制
  private String name; // name VARCHAR(100) NOT NULL
  private int age;

  @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
  private List<Product> products = new ArrayList<>();

  public User() {} // Hibernate 需要無參數 constructor

  public User(String name, int age) {
    this.name = name;
    this.age = age;
  }

  // Getter & Setter
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
