package org.example;

import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    // 建立 Morphia Datastore，連到本地端 MongoDB
    Datastore datastore = Morphia.createDatastore(MongoClients.create("mongodb://localhost:27017"), "testDB");
    datastore.getMapper().map(User.class);
    datastore.ensureIndexes();

    // 建立一個新使用者並儲存
    User user = new User("Angel", 25);
    datastore.save(user);
    System.out.println("✅ 已儲存使用者: " + user);

    // 查詢所有使用者
    List<User> users = datastore.find(User.class).iterator().toList();
    users.forEach(System.out::println);
  }
}
