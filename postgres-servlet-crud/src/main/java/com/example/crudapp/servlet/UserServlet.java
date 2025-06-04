package com.example.crudapp.servlet;

import com.example.crudapp.dao.UserDAO;
import com.example.crudapp.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter; // PrintWriter: can output text directly to the response
import java.util.ArrayList;
import java.util.List;

//HttpServlet: Interface that Provides support for HTTP requests.
//@WebServlet: Registers this Servlet as a handler for the /users path.
//HttpServletRequest / HttpServletResponse: represent HTTP request and response respectively.

@WebServlet("/users")
public class UserServlet extends HttpServlet {

  // UserDAO class for
  private UserDAO userDAO = new UserDAO();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // ServletException
    // Indicates an error that occurred during the execution of the Servlet
    // Servlet logic errors (for example: initialization failure, unable to process request format)
    // A call to another Servlet or JSP failed (for example, forward failed)
    // Internal business logic errors (for example, DAO layer errors but do not want to use IOException wrapper)


    String idParam = req.getParameter("id");
    String emailParam = req.getParameter("email");
    String ageParam = req.getParameter("age");

    List<User> users = new ArrayList<>();

    try {
      // Use a UserDAO object to operate the user data in the database
      if (idParam != null) {
        users = userDAO.getUsersById(Integer.parseInt(idParam)); //query id
      } else if (emailParam != null) {
        users = userDAO.getUsersByEmail(emailParam); //query email
      } else if (ageParam != null) {
        users = userDAO.getUsersByAge(Integer.parseInt(ageParam)); //query age
      } else {
        users = userDAO.getAllUsers(); // query all users
      }

      resp.setContentType("application/json"); // Sets HTTP response Content-Type as application/json. so that postman or frontend to process
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
      resp.setStatus(500);
      resp.getWriter().println("{\"error\":\"Query failed\"}");
      e.printStackTrace();
    }
  }

  // Add new user（use POST + x-www-form-urlencoded）
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    // Extract data from the request form and store it in the database
    String name = req.getParameter("name");
    String email = req.getParameter("email");
    int age = Integer.parseInt(req.getParameter("age")); // parse to Integer

    // Create a new User object and set its data
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setAge(age);

    // UserDAO for handling User-related database operations (CRUD)
    // Here Add user
    try {
      userDAO.addUser(user);
      resp.setStatus(201);
      resp.getWriter().println("{\"message\":\"User created successfully\"}");
    } catch (Exception e) {
      resp.setStatus(500);
      resp.getWriter().println("{\"error\":\"Failed to create user\"}");
      e.printStackTrace();
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    int id = Integer.parseInt(req.getParameter("id"));
    String name = req.getParameter("name");
    String email = req.getParameter("email");

    User user = new User();
    user.setId(id);
    user.setName(name);
    user.setEmail(email);

    try {
      userDAO.updateUser(user);
      resp.getWriter().println("{\"message\":\"User updated\"}");
    } catch (Exception e) {
      resp.setStatus(500);
      resp.getWriter().println("{\"error\":\"Update failed\"}");
      e.printStackTrace();
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    int id = Integer.parseInt(req.getParameter("id"));

    try {
      userDAO.deleteUser(id);
      resp.getWriter().println("{\"message\":\"User deleted\"}");
    } catch (Exception e) {
      resp.setStatus(500);
      resp.getWriter().println("{\"error\":\"Delete failed\"}");
      e.printStackTrace();
    }
  }



}
