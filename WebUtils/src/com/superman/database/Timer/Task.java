package com.superman.database.Timer;

import java.util.TimerTask;

public class Task extends TimerTask {

	@Override
	public void run() {
		while(true){
			System.out.println("定时任务执行");
			try {
				Thread.sleep(2  * 1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
