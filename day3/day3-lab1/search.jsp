<%@page import="java.util.ArrayList"%> 
<%@ page import="com.shaheen.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Ahmed Shaheen</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"-->
    <!--          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">-->
    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"> -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="static/css/myprofile.css">
</head>
<body>
<div class="container">
	<form method="POST" action="/day3-lab1/search">
		<input type="text" name="searchText" required="true">
		<button type="submit">search</button>
	</form>
	  <%
        ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
         if (users != null && users.size()>0) { 
        %>
    		
   	<table class="table table-striped table-bordered">
  		<thead class="thead-dark">
			<td scope="col">first name </td>
			<td scope="col">last name </td>
			<td scope="col">phone </td>
			<td scope="col">email </td>
		</thead>
		<thbody>
			<%for(User user:users){%> 
        <%-- Arranging data in tabular form 
        --%> 
            <tr> 
                <td  scope="row"><%=user.getFirstName()%></td>
                <td  scope="row"><%=user.getLastName()%></td> 
                <td  scope="row"><%=user.getPhone()%></td> 
                <td  scope="row"><%=user.getEmail()%></td> 
            </tr> 
            <%}%> 
		</thbody>

	</table>
        <%
          }
        %>
	
</div>
</body>
</html>