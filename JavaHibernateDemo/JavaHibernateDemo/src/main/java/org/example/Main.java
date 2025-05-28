package org.example;

import org.example.model.Product;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
  public static void main(String[] args) {
    // Step 1: 建立 Hibernate 的 SessionFactory
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")  // 讀取設定檔
        .addAnnotatedClass(User.class)   // 加入實體類別
        .addAnnotatedClass(Product.class) // Adding Product
        .buildSessionFactory();


    // Step 2: 開啟 Session
    try (Session session = factory.openSession()) {

      // Step 3: 開始交易
      session.beginTransaction();

      // Step 4: 插入一筆資料
      User newUser = new User("Angel", 28);
      session.persist(newUser);

      // Step 4: 建立 Product 並設定 buyer 是剛插入的 user
      Product product1 = new Product("iPhone", 999.0, 5, newUser);
      Product product2 = new Product("MacBook", 1999.0, 2, newUser);
      session.persist(product1);
      session.persist(product2);

      newUser.getProducts().add(product1);
      newUser.getProducts().add(product2);

      // Step 5: 查詢剛剛插入的資料
      User foundUser = session.get(User.class, newUser.getId());
      System.out.println("✅ 查詢成功: " + foundUser.getName() + " - " + foundUser.getAge());

      System.out.println("✅ 使用者: " + foundUser.getName());
      System.out.println("📦 他買了哪些商品：");

      for (Product product : foundUser.getProducts()) {
        System.out.println("- " + product.getName() + " / $" + product.getPrice());
      }

      // Step 6: 提交交易
      session.getTransaction().commit();
    }

    // Step 7: 關閉工廠（Optional）
    factory.close();
  }
}
