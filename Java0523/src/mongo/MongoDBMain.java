package mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

public class MongoDBMain {

  // Entry point of the program
  public static void main(String[] args) {
    // MongoDB connection URI (localhost with default port 27017)
    String uri = "mongodb://localhost:27017";

    // Try-with-resources to auto-close the client
    try (MongoClient mongoClient = MongoClients.create(uri)) { // create connection object (DNS parsing, TCP etc.)

      // Connect to database "testDB"
      MongoDatabase database = mongoClient.getDatabase("testDB"); // get a DB obj (lazy)

      // Access the "users" collection (MongoDB will auto-create if it doesn't exist)
      MongoCollection<Document> collection = database.getCollection("users");

      // Create a sample document to insert
      Document doc = new Document("name", "Angel")
          .append("age", 25)
          .append("email", "angel@example.com");

      // Insert the document into the collection
      // check if the email addr already exist
      if (collection.find(new Document("email", "angel@example.com")).first() == null) {
        collection.insertOne(doc);
        System.out.println("Inserted new user.");
      } else {
        System.out.println("User already exists. Skipped insertion.");
      }

      // Delete
      collection.deleteOne(Filters.eq("email", "angel@example.com")); // delete one
//      collection.deleteMany(Filters.eq("email", "angel@example.com")); // delete all
      System.out.println("Deleted one document with email = angel@example.com");


//      // Retrieve and print all documents in the collection
//      for (Document document : collection.find()) {
//        System.out.println(document.toJson());
//      }

      // Confirm deleted documents
      long count = collection.countDocuments(Filters.eq("email", "angel@example.com"));
      System.out.println("Remaining documents with that email: " + count);


    } catch (Exception e) {
      System.err.println("An error occurred while connecting to MongoDB:");
      e.printStackTrace();
    }
  }
}
