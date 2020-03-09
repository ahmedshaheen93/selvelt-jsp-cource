package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;

public class ServeletContextDemo implements Servlet{
	ServletConfig myConfig;
	
	public void init(ServletConfig config) throws ServletException
	{
		myConfig = config;
	}
	
	public void service(ServletRequest request,
						ServletResponse response)
						throws ServletException, IOException
	{
		ServletContext servletContext = myConfig.getServletContext();

		String auther = servletContext.getInitParameter("auther");
		
		String serviceName = (String)servletContext.getAttribute("serviceName");
		PrintWriter out = response.getWriter();
		out.println ("auther : "+auther);
		out.println ("serviceName : "+serviceName);
		

	}
	public void destroy(){
		System.out.println("I am inside the destroy method");
	}
	public String getServletInfo(){
		return null;
	}
	public ServletConfig getServletConfig(){
		return null;
	}
}
