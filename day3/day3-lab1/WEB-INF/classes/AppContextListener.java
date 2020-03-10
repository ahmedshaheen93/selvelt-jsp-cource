package com.shaheen.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.shaheen.config.database.connection.*;
import com.shaheen.service.*;
import com.shaheen.service.impl.*;

@WebListener
public class AppContextListener implements ServletContextListener{
	
	@Override
    public void contextInitialized (ServletContextEvent servletContextEvent) {
    	ServletContext servletContext = servletContextEvent.getServletContext();

		String dbUrl = servletContext.getInitParameter("DB-URL");
		String dbPort = servletContext.getInitParameter("DB-PORT");
		String dbName = servletContext.getInitParameter("DB-NAME");
		String dbUser = servletContext.getInitParameter("DB-USER");
		String dbPassword = servletContext.getInitParameter("DB-PASSWORD");
		ConnectToMysql connectionToMySql = new ConnectToMysql(dbUrl ,
			Integer.parseInt(dbPort) ,dbName ,dbUser ,dbPassword);
		servletContext.setAttribute("connectionToMySql" ,connectionToMySql);
		System.out.println("create db on context");
		ServerUserService userService 
		= new ServerUserServiceImpl(connectionToMySql);


		servletContext.setAttribute("userService" ,userService);
	}
	@Override
    public void contextDestroyed (ServletContextEvent sce) {

    }
}
