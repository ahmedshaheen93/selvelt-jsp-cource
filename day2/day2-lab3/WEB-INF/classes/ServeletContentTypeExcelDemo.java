package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;

public class ServeletContentTypeExcelDemo extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse response){
		try{
			response.setContentType("application/vnd.ms-excel");
			PrintWriter out = response.getWriter();
			out.println("\t jan \t feb \t march \t total");
			out.println("salary \t100 \t200 \t300 \t=sum(B2:D2)");
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp){

	}
}
