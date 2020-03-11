package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import com.shaheen.model.*;
import java.util.ArrayList;
import java.util.List;
import com.shaheen.service.*;

public class SqlTool extends HttpServlet{
	@Override
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
         RequestDispatcher rd = request.getRequestDispatcher("sqlTool.jsp");
        rd.include(request, response);
       
    }
    @Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws IOException, ServletException
    {
    	String sqlText =request.getParameter("sqlText").trim();
    	
       ServletContext context =  request.getServletContext();
		ServerUserService userService 
        =(ServerUserService)context.getAttribute("userService");
    	System.out.println(userService);
        if(sqlText.toUpperCase().startsWith("SELECT")){
        try{
            List<User> users = userService.excuteQuery(sqlText );
             if(users !=null && users.size()>0){
              request.setAttribute("users",users);
            }
           
        }catch(Exception e ){
        request.setAttribute("exception",e.getMessage());

        }
        }else{
            try{
                 int numOfRows =userService.excuteUpdate(sqlText );
            request.setAttribute("rowsUpdated",numOfRows);
            }catch(Exception e ){
            request.setAttribute("exception",e.getMessage());

        }
           

        }
    	
        RequestDispatcher rd = request.getRequestDispatcher("sqlTool.jsp");
        rd.include(request, response);

        
		
    }
}

