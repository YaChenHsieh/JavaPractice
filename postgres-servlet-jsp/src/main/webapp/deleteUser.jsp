<%--
  Created by IntelliJ IDEA.
  User: angelhsieh
  Date: 6/7/25
  Time: 1:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>

<h2>Delete User</h2>
<form id="deleteForm">
    ID: <input type="number" name="id" required><br>
    <button type="submit">Delete</button>
</form>

<a href="index.jsp">Back to User List</a>

<script>
  document.getElementById("deleteForm").addEventListener("submit", async function (event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const id = formData.get("id");
    console.log("🔍 formData is:", formData);
    console.log("🔍 id is:", id);

    const res = await fetch(`/postgres-servlet-jsp/jsp?id=${id}`, {
      method: "DELETE",
    });

    const data = await res.json();
    alert(res.ok ? data.message : data.error || "Delete failed"); // error message
    if (res.ok) {
      location.href = "index.jsp"; // render to user list if success
    }
  });
</script>


</body>
</html>
