package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
import com.shaheen.model.*;

import com.shaheen.service.*;

public class CheckCookie extends HttpServlet{
	@Override
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
     	     Cookie c1 = new Cookie("checkCookie", "true");
             response.addCookie(c1);
             response.sendRedirect(request.getContextPath() + "/login?cookie=true");
    }
    @Override
	 public void doPost(HttpServletRequest request, HttpServletResponse response)
    	throws IOException, ServletException
    {
		
    }
}

