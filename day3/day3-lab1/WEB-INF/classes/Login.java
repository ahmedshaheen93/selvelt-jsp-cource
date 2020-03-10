package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import com.shaheen.model.*;

import com.shaheen.service.*;

public class Login extends HttpServlet{
	@Override
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
         String error= request.getParameter("error");
        if(error!=null ){
             if( error.equals("true")){
                request.setAttribute("messages", "error username and password");
            }
        }
         RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.include(request, response);
       
    }
    @Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws IOException, ServletException
    {
    	String username =request.getParameter("username");
    	String password =request.getParameter("password");
       ServletContext context =  request.getServletContext();
		ServerUserService userService 
        =(ServerUserService)context.getAttribute("userService");
    	System.out.println(userService);
       User user = userService.findByPhoneAndPassword(username ,password );
    	if(user !=null && user.getId()>0){
    		 response.sendRedirect(request.getContextPath() + "/home");
    	}
        else{
            response.sendRedirect(request.getContextPath() + "/login?error=true");
        }		
		
    }
}
