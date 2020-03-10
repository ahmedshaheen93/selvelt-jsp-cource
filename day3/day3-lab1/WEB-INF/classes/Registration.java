package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import com.shaheen.model.*;

import com.shaheen.service.*;

public class Registration extends HttpServlet{
	@Override
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
         String error= request.getParameter("error");
        if(error!=null ){
             if( error.equals("true")){
                request.setAttribute("messages", "faild to register user");
            }
        }
         RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.include(request, response);
       
    }
    @Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws IOException, ServletException
    {
    	String firstName =request.getParameter("firstName");
    	String lastName =request.getParameter("lastName");
        String username =request.getParameter("username");
        String phone =request.getParameter("phone");
        String email =request.getParameter("email");
        String password =request.getParameter("password");
       User user =  new User();
       user.setFirstName(firstName);
        user.setLastName(lastName);
       user.setPhone(phone);
       user.setEmail(email);
       user.setPassword(password);

       ServletContext context =  request.getServletContext();
		ServerUserService userService 
        =(ServerUserService)context.getAttribute("userService");
    	System.out.println(userService);
        user = userService.insertUser(user,password);
    	if(user !=null && user.getId()>0){
    		 response.sendRedirect(request.getContextPath() + "/home");
    	}
        else{
            response.sendRedirect(request.getContextPath() + "/register?error=true");
        }		
		
    }
}
