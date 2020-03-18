<%@ page import="com.iti.day5.User" %>
<%@ page import="com.iti.day5.UserDAO" %>
<%@ page import="com.iti.day5.UserDAOImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: lts
  Date: ١٨‏/٣‏/٢٠٢٠
  Time: ٥:٥٤ م
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="user" class="com.iti.day5.User" scope="request"/>
<jsp:setProperty name="user" property="*"/>
<p>username :
    <jsp:getProperty name="user" property="username"/>
</p>
<p>password :
    <jsp:getProperty name="user" property="password"/>
</p>
<%
    if (user != null) {
        UserDAO userDAO = new UserDAOImpl();
        User loginUser = userDAO.login(user.getUsername(), user.getPassword());
        if (loginUser != null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
%>
</body>
</html>
