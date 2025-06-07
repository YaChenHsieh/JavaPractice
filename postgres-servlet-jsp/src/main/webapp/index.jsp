<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.HttpURLConnection, java.io.*, java.net.URL" %>
<%@ page import="java.util.Scanner" %>

<html>
<head>
    <title>User List</title>
</head>
<body>
<h2>All Users</h2>

<!-- 🔗 導覽連結列 -->
<a href="addUser.jsp">➕ Add New User</a> |
<a href="updateUser.jsp">✏️ Update User</a> |
<a href="deleteUser.jsp">🗑️ Delete User</a>
<br><br>

<table border="1">
    <tr>
        <th>ID</th><th>Name</th><th>Email</th><th>Age</th>
    </tr>

    <%
        URL url = new URL("http://localhost:8080/postgres-servlet-jsp/jsp");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        Scanner scanner = new Scanner(con.getInputStream());
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine());
        }
        scanner.close();
    %>
</table>

<script>
  const jsonStr = '<%= sb.toString().replaceAll("\"", "\\\\\"") %>';
  const users = JSON.parse(jsonStr);
  const table = document.querySelector("table");

  users.forEach(user => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${user.id}</td>
      <td>${user.name}</td>
      <td>${user.email}</td>
      <td>${user.age}</td>
    `;
    table.appendChild(row);
  });
</script>

</body>
</html>
