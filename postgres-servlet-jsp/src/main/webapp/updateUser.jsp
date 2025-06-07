<%--
  Created by IntelliJ IDEA.
  User: angelhsieh
  Date: 6/7/25
  Time: 1:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>

<h2>Update User</h2>
<form id="updateForm">
    ID: <input type="number" name="id" required><br>
    New Name: <input type="text" name="name"><br>
    New Email: <input type="email" name="email"><br>
    <button type="submit">Update</button>
</form>

<a href="index.jsp">Back to User List</a>

<script>
  document.getElementById("updateForm").addEventListener("submit", async function (event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const id = formData.get("id");
    const name = formData.get("name");
    const email = formData.get("email");

    const res = await fetch(`/postgres-servlet-jsp/jsp?id=${id}&name=${encodeURIComponent(name)}&email=${encodeURIComponent(email)}`, {
      method: "PUT",
    });

    const data = await res.json();
    alert(res.ok ? data.message : data.error || "Update failed");
    if (res.ok) {
      location.href = "index.jsp";  // 導回首頁
    }
  });
</script>

</body>
</html>
