package com.superman.Timer;

import java.util.TimerTask;

public class TomcatOnLoadTask extends TimerTask {

	@Override
	public void run() {
		while(true){
			System.out.println("httpServlet��ʽ��ʱ����ִ��");
			try {
				Thread.sleep(2  * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
