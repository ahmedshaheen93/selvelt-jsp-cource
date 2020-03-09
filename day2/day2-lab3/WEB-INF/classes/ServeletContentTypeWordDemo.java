package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class ServeletContentTypeWordDemo extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response){
		try{
			response.setContentType("application/msword");
			PrintWriter out = response.getWriter();
			out.println("first word application from servelt");
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp){

	}
}
