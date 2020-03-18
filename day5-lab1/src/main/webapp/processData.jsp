<%@ page import="com.iti.day5.UserDAO" %>
<%@ page import="com.iti.day5.UserDAOImpl" %>
<%--
  Created by IntelliJ IDEA.
  User: lts
  Date: ١٨‏/٣‏/٢٠٢٠
  Time: ٥:٢٨ م
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

<p> first name =
    <jsp:getProperty name="user" property="firstName"/>
</p>
<p> first name =
    <jsp:getProperty name="user" property="lastName"/>
</p>
<p> first name =
    <jsp:getProperty name="user" property="username"/>
</p>
<%
    UserDAO userDAO = new UserDAOImpl();
    userDAO.register(user);
    if (user != null && user.getId() > 0) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    } else {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
        requestDispatcher.forward(request, response);
    }
%>

</body>
</html>
