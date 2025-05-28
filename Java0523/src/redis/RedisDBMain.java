package redis;

import redis.clients.jedis.Jedis;
import java.util.Map;
import java.util.List;
import java.util.Set;


public class RedisDBMain {
  public static void main(String[] args) {
    try (Jedis jedis = new Jedis("localhost", 6379)) {

      // --------- CREATE / UPDATE ----------
      jedis.set("name", "Crystal");               // String
      jedis.hset("user:1002", "email", "crystal@example.com");  // Hash
      jedis.lpush("chores", "c1", "c2");    // List
      jedis.sadd("skills", "Chem", "Art");    // Set

//      // --------- READ ----------
      System.out.println("Name: " + jedis.get("name"));
      String result = jedis.set("name", "Crystal");
      System.out.println("SET result: " + result);  // OK，otherwise meaning not connect successfully

//      System.out.println("Email: " + jedis.hget("user:1001", "email"));
//      System.out.println("First TODO: " + jedis.lpop("todo"));
//      System.out.println("Has skill Redis? " + jedis.sismember("skills", "Redis"));
//
//      // --------- UPDATE ----------
//      jedis.hset("user:1001", "email", "new_email@example.com");
//
//      // --------- DELETE ----------
//      jedis.del("name");              // Delete key
//      jedis.hdel("user:1001", "email"); // Delete field in hash
//      jedis.srem("skills", "Java");   // Remove from set

//      // --------- QUERY ----------
//      // String
//      System.out.println("name: " + jedis.get("name"));
//
//      // Hash
//      Map<String, String> user = jedis.hgetAll("user:1001");
//      System.out.println("user:1001");
//      user.forEach((k, v) -> System.out.println("  " + k + " = " + v));
//
//      // List
//      List<String> todo = jedis.lrange("todo", 0, -1);
//      System.out.println("todo list: " + todo);
//
//      // Set
//      Set<String> skills = jedis.smembers("skills");
//      System.out.println("skills: " + skills);
    }
  }
}
