import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgresTest {
  public static void main(String[] args) {
    // JDBC connect setup
    String url = "jdbc:postgresql://localhost:5432/postgres";
    String user = "postgres";
    String password = "postpwd";

    try (Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = conn.createStatement()) {

      System.out.println(" Connected to PostgreSQL！");

      // Create a table
      String sql = "CREATE TABLE IF NOT EXISTS test_table (id SERIAL PRIMARY KEY, name TEXT)";
      stmt.executeUpdate(sql);
      System.out.println("Created: test_table");

    } catch (Exception e) {
      System.err.println("Error：" + e.getMessage());
      e.printStackTrace();
    }
  }
}
