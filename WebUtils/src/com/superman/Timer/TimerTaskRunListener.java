package com.superman.Timer;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ��ʱ�����Զ���ʱ��������������ʹ��tomcat������
 * @author Ningsc(supermanNingsc)
 *
 */
public class TimerTaskRunListener implements ServletContextListener {
	
	private Timer timer = null;

	/**
	 * ������رռ��������������������ٶ�ʱ���� 
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("��ʱ������");
	}

	/**
	 * �������ʼ������������tomcat������ʱ�����������������������ʵ�ֶ�ʱ������ 
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		timer = new Timer(true);
		event.getServletContext().log("��ʱ������");
		timer.schedule(new TomcatOnLoadTask(), new Date(), 2000);
		event.getServletContext().log("��ʱ����������");
	}

}
