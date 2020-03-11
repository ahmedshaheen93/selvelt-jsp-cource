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
    
        String cheackCookie= request.getParameter("cookie");
        // geting from old request 
        if(cheackCookie!=null ){
            boolean enableCookie = false ;
             if( cheackCookie.equals("true")){
				Cookie[] cookies = request.getCookies();
                if(cookies != null)
                {
                  for (int i=0; i<cookies.length; i++){
                       Cookie cookie = cookies[i];
                        if(cookie.getName().equals("checkCookie")){
                            enableCookie =true;
                        }
                    }
                }
                if(!enableCookie){
                    request.setAttribute("messages", "please enable cookie to go on");
                }

            }
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.include(request, response);
        }else{
            response.sendRedirect(request.getContextPath() + "/checkCookie");
        }
       
    }
    @Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws IOException, ServletException
    {
    	String username =request.getParameter("username");
    	String password =request.getParameter("password");
        ServletContext context =  request.getServletContext();
		ServerUserService userService  =(ServerUserService)context.getAttribute("userService");
    	System.out.println(userService);
          User user = userService.findByPhoneAndPassword(username ,password );
    	if(user !=null && user.getId()>0){
             Cookie c1 = new Cookie("usernameAndPassword", "{ username:"+username+",password:"+password+"}");
             response.addCookie(c1);
            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.include(request, response);

    	}
        else{
            request.setAttribute("messages", "error username and password");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.include(request, response);
        }		
		
    }
}
