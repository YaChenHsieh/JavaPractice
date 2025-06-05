package com.example.servletapp.servlet;

import com.example.servletapp.dao.UserDAO;
import com.example.servletapp.model.User;
import jakarta.servlet.*; //Core interface of Servlet

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//The most primitive and lowest-level Servlet specification interface
// 5 methods
// init(ServletConfig config) throws ServletException;
// getServletConfig();
// service(ServletRequest req, ServletResponse res) throws ServletException, IOException;
// getServletInfo();
// destroy();


public class UserServlet implements Servlet {


  private ServletConfig config;
  private UserDAO userDAO;
  // ServletConfig config: Provided by the container (Tomcat) when the Servlet is initialized,
  // including: servlet name, context, <init-param> parameters in web.xml
  // The config is stored for later return using getServletConfig().

  @Override
  public void init(ServletConfig config) { // init() is called only once when the Servlet is loaded.
    this.config = config;
    this.userDAO = new UserDAO();
  }
//  .setContentType
//  HTML (text/html): Display web pages
//  JSON (application/json): front-end and back-end separation (AJAX, React, Vue, etc.)
//  XML (application/xml): Legacy web services or device integration
//  Text/plain: testing or simple API
//  Binary (application/octet-stream): file download, image upload
  @Override
  public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    PrintWriter out = resp.getWriter(); // Write HTML to client：getWriter()

    // from postman -> http://localhost:8080/yourapp/users?action=create&name=Tom&email=tom@example.com
    String action = req.getParameter("action");

    try {
      if ("create".equalsIgnoreCase(action)) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String ageStr = req.getParameter("age");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        if (ageStr != null && !ageStr.isEmpty()) {
          user.setAge(Integer.parseInt(ageStr));
        }

        userDAO.addUser(user);
        out.println("<h2>User created!</h2>");

      } else {
        List<User> users = userDAO.getAllUsers();
        out.println("<h2>User List</h2>");
        for (User user : users) {
          out.printf("<p>ID: %d, Name: %s, Email: %s, Age: %d</p>%n",
              user.getId(), user.getName(), user.getEmail(), user.getAge());
        }
      }
    } catch (Exception e) {
      e.printStackTrace(out);
    }
  }

  @Override
  public void destroy() {}

  @Override
  public String getServletInfo() {
    return "Raw Servlet example for User CRUD";
  }

  @Override
  public ServletConfig getServletConfig() {
    return config;
  }
}
