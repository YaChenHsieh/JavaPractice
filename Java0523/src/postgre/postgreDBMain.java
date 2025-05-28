package postgre;

// Import JDBC API
import java.sql.Connection; // Database connection object
import java.sql.DriverManager; // responsible for establishing the connection
import java.sql.PreparedStatement; // Precompiled SQL statement object
import java.sql.ResultSet; // query result set object (such as a table)


public class postgreDBMain {
  public static void main(String[] args) {
    try {
      Class.forName("org.postgresql.Driver"); // 可選，手動註冊 driver

      // JDBC connection string
      // jdbc: => specifies the use of JDBC protocol
      // postgresql: => specifies to use the PostgreSQL driver
      // localhost => host name (here is the local machine)
      // 5432 => PostgreSQL default port number
      // /postgres => specifies the database name to connect to is postgres
      String url = "jdbc:postgresql://localhost:5432/postgres";
      String user = "postgres";
      String password = "postpwd";

      try (Connection conn = DriverManager.getConnection(url, user, password)) {
        System.out.println("✅ PostgreSQL connected!");

        // Step 4: 準備 SQL 查詢語句
        String sql = "SELECT * FROM users WHERE age > ?";
        PreparedStatement stmt = conn.prepareStatement(sql); // 預備SQL
        stmt.setInt(1, 25); // ← 這裡才是真正的「動態設定參數」

        // Step 5: 執行查詢並取得結果
        ResultSet rs = stmt.executeQuery();

        // Step 6: 處理查詢結果
        while (rs.next()) {
          int id = rs.getInt("id");
          String name = rs.getString("name");
          int age = rs.getInt("age");
          System.out.println("ID: " + id + name + " is " + age);
        }
        // Step 7: 不需要手動關閉 conn / stmt / rs，因為用了 try-with-resources
      }//try

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
