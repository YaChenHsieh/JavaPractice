package com.example.jsp.dao;

import com.example.jsp.model.User;
import com.example.jsp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

  // CREATE - add new user
  public void addUser(Connection conn, User user) throws SQLException {
    String sql = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, user.getName());
      stmt.setString(2, user.getEmail());
      stmt.setInt(3, user.getAge());
      stmt.executeUpdate();
    }
  }

  // READ - query all users
  public List<User> getAllUsers(Connection conn) throws SQLException {
    List<User> list = new ArrayList<>();
    String sql = "SELECT * FROM users";

    try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setAge(rs.getInt("age")); // optional 如果你的 User class 有這欄位
        list.add(u);
      }
    }

    return list;
  }


  // READ - query single data
  public List<User> getUsersById(Connection conn, int id) throws SQLException {
    String sql = "SELECT * FROM users WHERE id = ?";
    List<User> users = new ArrayList<>();

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        users.add(mapResultSetToUser(rs));
      }
    }

    return users;
  }

  public List<User> getUsersByEmail(Connection conn, String email) throws SQLException {
    String sql = "SELECT * FROM users WHERE email = ?";
    List<User> users = new ArrayList<>();

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        users.add(mapResultSetToUser(rs));
      }
    }

    return users;
  }


  public List<User> getUsersByAge(Connection conn, int age) throws SQLException {
    String sql = "SELECT * FROM users WHERE age = ?";
    List<User> users = new ArrayList<>();
    try ( PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, age);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        users.add(mapResultSetToUser(rs));
      }
    }
    return users;
  }

  private User mapResultSetToUser(ResultSet rs) throws SQLException {
    User u = new User();
    u.setId(rs.getInt("id"));
    u.setName(rs.getString("name"));
    u.setEmail(rs.getString("email"));
    u.setAge(rs.getInt("age"));
    return u;
  }

  // UPDATE user info
  public void updateUser(Connection conn, User user) throws SQLException {
    String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";

    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, user.getName());
      stmt.setString(2, user.getEmail());
      stmt.setInt(3, user.getId());
      stmt.executeUpdate();
    }
  }


  // DELETE - 刪除使用者
  public void deleteUser(Connection conn, int id) throws SQLException {
    String sql = "DELETE FROM users WHERE id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    }
  }

}
