package com.superman.Timer;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 定时器,随tomcat启动而启动
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
		timer.cancel();//销毁定时器
	}

	/**
	 * 初始化定时任务
	 */
	public void init() throws ServletException {
		 //java中jdk自带的时间工具类
		timer.schedule(new TomcatOnLoadTask(), new Date(), 2000);//该定时时间需根据项目需求而设置
	}

}
