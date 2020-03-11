package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import com.shaheen.model.*;
import java.util.ArrayList;
import java.util.List;
import com.shaheen.service.*;

public class Search extends HttpServlet{
	@Override
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    //      String error= request.getParameter("error");
    //     if(error!=null ){
    //          if( error.equals("true")){
    //             request.setAttribute("messages", "error username and password");
    //         }
    //     }
         RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
        rd.include(request, response);
       
    }
    @Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws IOException, ServletException
    {
    	String searchText =request.getParameter("searchText");
    	
       ServletContext context =  request.getServletContext();
		ServerUserService userService 
        =(ServerUserService)context.getAttribute("userService");
    	System.out.println(userService);
        List<User> users = userService.search(searchText );
    	
        if(users !=null && users.size()>0){
    		 // response.sendRedirect(request.getContextPath() + "/home");
    	request.setAttribute("users",users);
        RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
        rd.include(request, response);

        }
        else{
            response.sendRedirect(request.getContextPath() + "/search?error=true");
        }		
		
    }
}

