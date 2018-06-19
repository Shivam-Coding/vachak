package com.udgaman.vachak.util;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.udgaman.vachak.repository.InitialRepository;

public class VachakServletContextListener implements ServletContextListener{
	
	@Autowired
	private InitialRepository initialRepository;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		WebApplicationContextUtils
        .getRequiredWebApplicationContext(arg0.getServletContext())
        .getAutowireCapableBeanFactory()
        .autowireBean(this);
		
		List<String> users = this.initialRepository.onlineStatus();
		for(String u : users){
		OnlineStatus.initialize(u, "offline");
		}
		
		Thread thread = new Thread(new InitializeOnlineStatus(), "InitializeOnlineStatus");
		thread.start();
		
	}

}
