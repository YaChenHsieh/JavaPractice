package com.example.jsp.servlet;


import com.example.jsp.dao.UserDAO;
import com.example.jsp.model.User;

import com.example.jsp.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter; // PrintWriter: can output text directly to the response
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//HttpServlet: Interface that Provides support for HTTP requests.
//@WebServlet: Registers this Servlet as a handler for the /users path.
//HttpServletRequest / HttpServletResponse: represent HTTP request and response respectively.
// URL test -> pom.xml (<finalName>postgres-servlet-jsp</finalName>) so postgres-servlet-jsp is war name, hence, http://localhost:8080/postgres-servlet-jsp/


@WebServlet("/jsp")
public class UserServlet extends HttpServlet {

  // UserDAO class for
  private UserDAO userDAO = new UserDAO();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String idParam = req.getParameter("id");
    String emailParam = req.getParameter("email");
    String ageParam = req.getParameter("age");

    List<User> users = new ArrayList<>();
    Connection conn = null;

    try {
      conn = DBUtil.getConnection();
      conn.setAutoCommit(false);  // 開始交易（雖然是查詢）

      // 查詢邏輯
      if (idParam != null) {
        users = userDAO.getUsersById(conn, Integer.parseInt(idParam));
      } else if (emailParam != null) {
        users = userDAO.getUsersByEmail(conn, emailParam);
      } else if (ageParam != null) {
        users = userDAO.getUsersByAge(conn, Integer.parseInt(ageParam));
      } else {
        users = userDAO.getAllUsers(conn);
      }

      conn.commit();  // 查詢成功可提交（其實查詢類型不會改資料）

      resp.setContentType("application/json");
      PrintWriter out = resp.getWriter();
      out.println("[");
      for (int i = 0; i < users.size(); i++) {
        User u = users.get(i);
        out.printf("{\"id\":%d,\"name\":\"%s\",\"email\":\"%s\",\"age\":%d}%s\n",
            u.getId(), u.getName(), u.getEmail(), u.getAge(),
            (i < users.size() - 1 ? "," : ""));
      }
      out.println("]");

    } catch (Exception e) {
      if (conn != null) {
        try {
          conn.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
      resp.setStatus(500);
      resp.getWriter().println("{\"error\":\"Query failed\"}");
      e.printStackTrace();
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    }
  }


  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String name = req.getParameter("name");
    String email = req.getParameter("email");
    int age = Integer.parseInt(req.getParameter("age"));

    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setAge(age);

    Connection conn = null;
    try {
      conn = DBUtil.getConnection();
      conn.setAutoCommit(false); // 開始手動交易

      userDAO.addUser(conn, user); // 將 conn 傳進 DAO

      // 如果你未來想在這裡加入 log 記錄、新增角色... 都可以寫在這裡

      conn.commit(); // 所有操作成功，提交交易
      resp.setStatus(201);
      resp.getWriter().println("{\"message\":\"User created successfully\"}");
    } catch (Exception e) {
      if (conn != null) {
        try {
          conn.rollback(); // 發生錯誤就回滾
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
      resp.setStatus(500);
      resp.getWriter().println("{\"error\":\"Transaction failed, rollback\"}");
      e.printStackTrace();
    } finally {
      if (conn != null) {
        try {
          conn.close(); // 關閉資源
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    }
  }


  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    int id = Integer.parseInt(req.getParameter("id"));
    String name = req.getParameter("name");
    String email = req.getParameter("email");

    System.out.println("✅ PUT Request Received: id=" + id + ", name=" + name + ", email=" + email);

    User user = new User();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);

    Connection conn = null;

    try {
      conn = DBUtil.getConnection();
      conn.setAutoCommit(false); // 🔁 手動交易控制開始

      userDAO.updateUser(conn, user); // 傳入外部 connection

      conn.commit(); // ✅ 成功就提交
      resp.getWriter().println("{\"message\":\"User updated\"}");
    } catch (Exception e) {
      if (conn != null) {
        try {
          conn.rollback(); // ❌ 發生錯誤就回滾
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
      resp.setStatus(500);
      resp.getWriter().println("{\"error\":\"Update failed\"}");
      e.printStackTrace();
    } finally {
      if (conn != null) {
        try {
          conn.close(); // 🧹 關閉連線
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }
    }
  }


  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    int id = Integer.parseInt(req.getParameter("id"));

    Connection conn = null;

    try {
      conn = DBUtil.getConnection();
      conn.setAutoCommit(false); // 🔁 開始手動交易

      userDAO.deleteUser(conn, id); // DAO 接收外部傳進的 conn

      conn.commit(); // ✅ 成功就提交
      resp.getWriter().println("{\"message\":\"User deleted\"}");
    }
    catch (Exception e) {
      if (conn != null) {
        try {
          conn.rollback();
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
      }

      resp.setStatus(500);
      resp.setContentType("application/json"); // ✅ 讓前端知道這是 JSON
      String msg = e.getMessage().replace("\"", "\\\""); // 防止錯誤訊息有雙引號
      resp.getWriter().println("{\"error\": \"" + msg + "\"}"); // ✅ 寫回錯誤 JSON
      e.printStackTrace(); // ✅ 後端 log 顯示
    }
    finally {
      if (conn != null) {
        try {
          conn.close(); // 🧹 關閉連線
        } catch (SQLException e) {
          e.printStackTrace(); // ❗ 印出錯誤內容到 console
          resp.setStatus(500);
          resp.getWriter().println("{\"error\": \"" + e.getMessage().replace("\"", "\\\"") + "\"}");
        }

      }
    }
  }


}
