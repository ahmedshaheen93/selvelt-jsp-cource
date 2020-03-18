<%--
  Created by IntelliJ IDEA.
  User: lts
  Date: ١٨‏/٣‏/٢٠٢٠
  Time: ٤:٠١ م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form method="post" action="processData.jsp">
    <input type="text" name="firstName" required>
    <input type="text" name="lastName" required>
    <input type="text" name="username" required>
    <input type="password" name="password" required>
    <button type="submit">register</button>
</form>
</body>
</html>
