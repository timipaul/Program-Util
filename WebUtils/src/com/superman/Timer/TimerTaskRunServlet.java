package com.superman.Timer;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * ��ʱ��,��tomcat����������
 * @author Ningsc(supermanNingsc)
 *
 */
public class TimerTaskRunServlet extends HttpServlet {
	Timer timer = null;
	
	public TimerTaskRunServlet() {
		super();
		timer = new Timer(true);
	}

	
	public void destroy() {
		super.destroy(); 
		timer.cancel();//���ٶ�ʱ��
	}

	/**
	 * ��ʼ����ʱ����
	 */
	public void init() throws ServletException {
		 //java��jdk�Դ���ʱ�乤����
		timer.schedule(new TomcatOnLoadTask(), new Date(), 2000);//�ö�ʱʱ���������Ŀ���������
	}

}
