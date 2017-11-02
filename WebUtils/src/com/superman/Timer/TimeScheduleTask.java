package com.superman.Timer;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * �ö�ʱ���������̳߳صķ�ʽ��������
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
		System.out.println("����ʼ����ǰʱ�䣺" + dateFormat.format(new Date()));

        try {
            System.out.println("ģ����������...");
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.err);
        }

        System.out.println("�����������ǰʱ�䣺" + dateFormat.format(new Date()));
    }
	

}
