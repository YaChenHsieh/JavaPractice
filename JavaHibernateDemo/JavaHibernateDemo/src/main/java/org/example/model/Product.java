package org.example.model;

import jakarta.persistence.*;

@Entity // 表示這是一個可映射到資料庫的實體類（Entity）
@Table(name = "products") // 指定對應的資料表名稱為 "products"
public class Product {

  @Id // 指定主鍵
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 主鍵自動遞增（對應 PostgreSQL 的 SERIAL）
  private int id;

  @Column(nullable = false) // name 欄位不可為 null
  private String name;

  private double price; // 價格（可為 null）

  private int stock; // 庫存數量（可為 null）

  // 多對一關聯：多個產品對應一位使用者（買家）
  @ManyToOne
  @JoinColumn(name = "user_id") // 在 products 表中新增欄位 user_id 作為外鍵，連到 users 表的 id
  private User buyer;

  // 建構子：Hibernate 需要預設無參數建構子
  public Product() {}

  // 建構子：建立新商品時指定 name、price、stock、buyer
  public Product(String name, double price, int stock, User buyer) {
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.buyer = buyer;
  }
  // influence insertion in SQL
  //  INSERT INTO products (name, price, stock, user_id)
  //  VALUES ('iPhone', 999.99, 3, 1);


  // Getter & Setter 方法
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public User getBuyer() {
    return buyer;
  }

  public void setBuyer(User buyer) {
    this.buyer = buyer;
  }
}
