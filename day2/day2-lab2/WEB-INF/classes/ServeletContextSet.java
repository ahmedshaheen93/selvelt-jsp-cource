package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;

public class ServeletContextSet implements Servlet{
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
		servletContext.setAttribute("serviceName","myServiceName");
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
