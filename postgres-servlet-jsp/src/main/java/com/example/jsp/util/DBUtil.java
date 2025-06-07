package com.example.jsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

  private static final String URL = "jdbc:postgresql://localhost:5432/postgres"; // defaultDB name:postgres
  private static final String USER = "postgres";
  private static final String PASSWORD = "postpwd";

  public static Connection getConnection() throws SQLException {
    try {
      // auto add PostgreSQL JDBC Driver
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return DriverManager.getConnection(URL, USER, PASSWORD);
  }
}
