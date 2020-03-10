package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;

public class Home extends HttpServlet{
	@Override
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
       RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
        rd.include(request, response);
    }
}
