package com.superman.Timer;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 该定时任务是用线程池的方式进行启动
 * @author Ningsc(supermanNingsc)
 *
 */
public class TimeScheduleTask implements Runnable {
	
	public  int sleepTime;
	
	public SimpleDateFormat dateFormat;
	
	public TimeScheduleTask(int sleepTime){
		 this.sleepTime = sleepTime;
         dateFormat = new SimpleDateFormat("HH:mm:ss");
	}
	

	@Override
	public void run() {
		System.out.println("任务开始，当前时间：" + dateFormat.format(new Date()));

        try {
            System.out.println("模拟任务运行...");
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.println("任务结束，当前时间：" + dateFormat.format(new Date()));
    }
	

}
