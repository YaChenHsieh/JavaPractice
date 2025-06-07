<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add New User</title>
</head>
<body>

<h2>Add New User</h2>
<form id="userForm">
  Name: <input type="text" name="name" required><br>
  Email: <input type="email" name="email" required><br>
  Age: <input type="number" name="age" required><br>
  <button type="submit">Submit</button>
</form>

<a href="index.jsp">Go back to Users list</a>
<a href="/postgres-servlet-jsp/index.jsp">
  <button type="button">Go back to Users list</button>
</a>


<script>
  document.getElementById("userForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // ❌ 不要重新整理頁面

    const form = event.target;
    const formData = new FormData(form);

    // ⬇️ 使用 fetch 傳送 POST 請求到 Servlet
    const res = await fetch("/postgres-servlet-jsp/jsp", {
      method: "POST",
      body: new URLSearchParams(formData), // 送出為 x-www-form-urlencoded
    });

    const json = await res.json();

    if (res.ok) {
      alert(json.message); // ✅ 成功後跳出訊息
      form.reset(); // ✅ 清空表單
    } else {
      alert(json.error || "Unknown error");
    }
  });
</script>

</body>
</html>
