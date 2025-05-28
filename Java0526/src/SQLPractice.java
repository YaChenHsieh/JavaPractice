import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLPractice {
  public static void main(String[] args) {
    try {
      Class.forName("org.postgresql.Driver"); // ✅ 建議這行包在 try 裡

      String url = "jdbc:postgresql://localhost:5432/testdb";
      String user = "postgres";
      String password = "postgres";

      try (Connection conn = DriverManager.getConnection(url, user, password);
          Statement stmt = conn.createStatement()) {

        System.out.println("✅ Connected to PostgreSQL");

        // 建立表格
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS employees (" +
            "id SERIAL PRIMARY KEY, name TEXT, age INT)");

        // 插入資料
        stmt.executeUpdate("INSERT INTO employees (name, age) VALUES " +
            "('Alice', 24), ('Bob', 30)");

        // 查詢資料
        ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
        System.out.println("📋 Query Results:");
        while (rs.next()) {
          int id = rs.getInt("id");
          String name = rs.getString("name");
          int age = rs.getInt("age");
          System.out.printf("👤 ID: %d | Name: %s | Age: %d%n", id, name, age);
        }

      }
    } catch (Exception e) {
      System.err.println("❌ Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

}
