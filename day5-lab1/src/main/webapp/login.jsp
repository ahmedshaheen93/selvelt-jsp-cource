<%--
  Created by IntelliJ IDEA.
  User: lts
  Date: ١٨‏/٣‏/٢٠٢٠
  Time: ٤:٠٠ م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form method="post" action="loginProcess.jsp">
    <%--    //  session | page | request | application --%>
    <input type="text" name="username">
    <input type="password" name="password">
    <button type="submit">login</button>
</form>
</body>
</html>
