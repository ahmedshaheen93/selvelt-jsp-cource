package com.shaheen.serveltDemo;

import javax.servlet.*;
import java.io.*;

public class ServelteConfigDemo implements Servlet{
		ServletConfig myConfig;
	public void init(ServletConfig config) throws ServletException
	{
		myConfig = config;
	}
	public void service(ServletRequest request,
						ServletResponse response)
						throws ServletException, IOException
	{
		String dbUrl = myConfig.getInitParameter("DB-URL");
		String dbName = myConfig.getInitParameter("DB-NAME");
		String dbUser = myConfig.getInitParameter("DB-USER");
		String dbPassword = myConfig.getInitParameter("DB-PASSWORD");

		PrintWriter out = response.getWriter();
		out.println ("database url : "+dbUrl);
		out.println ("database name : " +dbName);
		out.println ("database user : " +dbUser);
		out.println ("database password : " +dbPassword);
	}
	public void destroy(){
	}
	public String getServletInfo(){
		return null;
	}
	public ServletConfig getServletConfig(){
		return null;
	}
}
