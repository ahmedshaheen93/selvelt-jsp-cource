package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;

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
         RequestDispatcher rd = request.getRequestDispatcher("index.html");
        rd.sendRedirect(request, response);
       
    }
    @Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws IOException, ServletException
    {
    	String username =request.getParameter("username");
    	String password =request.getParameter("password");
        
    	if(username.equals("ahmed")&& password.equals("password")){
    		 response.sendRedirect(request.getContextPath() + "/home");
    	}
        else{
            response.sendRedirect(request.getContextPath() + "/login?error=true");
        }		
		
    }
}
