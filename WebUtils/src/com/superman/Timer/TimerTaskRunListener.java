package com.superman.Timer;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 定时器（自动定时完成任务操作），使用tomcat监听器
 * @author Ningsc(supermanNingsc)
 *
 */
public class TimerTaskRunListener implements ServletContextListener {
	
	private Timer timer = null;

	/**
	 * 在这里关闭监听器，所以在这里销毁定时器。 
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}

	/**
	 * 在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能 
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		timer = new Timer(true);
		event.getServletContext().log("定时器启动");
		timer.schedule(new TomcatOnLoadTask(), new Date(), 2000);
		event.getServletContext().log("定时器任务启动");
	}

}
