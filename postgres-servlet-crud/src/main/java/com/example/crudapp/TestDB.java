package com.example.crudapp;

import com.example.crudapp.util.DBUtil;

import java.sql.Connection;

public class TestDB {
  public static void main(String[] args) {
    try (Connection conn = DBUtil.getConnection()) {
      System.out.println("✅ PostgreSQL connected!");
    } catch (Exception e) {
      System.out.println("❌ Connection failed:");
      e.printStackTrace();
    }
  }
}
