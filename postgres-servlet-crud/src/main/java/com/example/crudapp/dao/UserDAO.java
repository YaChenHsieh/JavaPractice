package com.example.crudapp.dao;

import com.example.crudapp.model.User;
import com.example.crudapp.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

  // CREATE - add new user
  public void addUser(User user) throws Exception {
    String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, user.getName());
      stmt.setString(2, user.getEmail());
      stmt.executeUpdate();
    }
  }

  // READ - query all users
  public List<User> getAllUsers() throws Exception {
    List<User> list = new ArrayList<>();
    String sql = "SELECT * FROM users";

    try (Connection conn = DBUtil.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {

      while (rs.next()) {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        list.add(u);
      }
    }

    return list;
  }

  // READ - query single data
  public List<User> getUsersById(int id) throws SQLException {
    String sql = "SELECT * FROM users WHERE id = ?";
    List<User> users = new ArrayList<>();

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      while (rs.next()) {
        users.add(mapResultSetToUser(rs));
      }
    }

    return users;
  }


  public List<User> getUsersByEmail(String email) throws SQLException {
    String sql = "SELECT * FROM users WHERE email = ?";
    List<User> users = new ArrayList<>();
    try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
      stmt.setString(1, email);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        users.add(mapResultSetToUser(rs));
      }
    }
    return users;
  }

  public List<User> getUsersByAge(int age) throws SQLException {
    String sql = "SELECT * FROM users WHERE age = ?";
    List<User> users = new ArrayList<>();
    try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
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
  public void updateUser(User user) throws Exception {
    String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setString(1, user.getName());
      stmt.setString(2, user.getEmail());
      stmt.setInt(3, user.getId());
      stmt.executeUpdate();
    }
  }

  // DELETE - 刪除使用者
  public void deleteUser(int id) throws Exception {
    String sql = "DELETE FROM users WHERE id = ?";

    try (Connection conn = DBUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

      stmt.setInt(1, id);
      stmt.executeUpdate();
    }
  }
}
