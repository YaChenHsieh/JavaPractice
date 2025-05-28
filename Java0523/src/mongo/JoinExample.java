package mongo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.AggregateIterable;
import org.bson.Document;
import org.bson.types.ObjectId;

import static java.util.Arrays.asList;

public class JoinExample {
  public static void main(String[] args) {
    try (MongoClient client = MongoClients.create("mongodb://localhost:27017")) {
      MongoDatabase db = client.getDatabase("testDB");
      MongoCollection<Document> orders = db.getCollection("orders");

      // insert data into orders collection
      // find the userid from users collection
//      ObjectId userId = new ObjectId("683422df8c58c81e2c008937");
//
//      Document order = new Document("item", "MacBook")
//          .append("price", 1999)
//          .append("user_id", userId);
//
//      orders.insertOne(order);
//      System.out.println("Inserted order with user_id.");

      // Aggregation pipeline with $lookup
      AggregateIterable<Document> result = orders.aggregate(asList(
          new Document("$lookup", new Document()
              .append("from", "users")                  // == SQL: JOIN users
              .append("localField", "user_id")          // orders.user_id (foreign Key)
              .append("foreignField", "_id")            // users._id (primary Key)
              .append("as", "user_info"))               // save the result in orders.user_info col
      ));

      for (Document doc : result) {
        System.out.println(doc.toJson());
      }
    }
  }
}
