package com.superman.Timer;

import java.util.Timer;

public class TimerTest {

	public static void main(String[] args) {
		/*ScheduledExecutorService timer =  Executors.newSingleThreadScheduledExecutor();
		TimeTask timeTask = new TimeTask(2000);
		System.out.printf("起始时间：%s\n\n", new SimpleDateFormat("HH:mm:ss").format(new Date()));

        // 延时 1 秒后，按 3 秒的周期执行任务
        timer.scheduleAtFixedRate(timeTask, 1000, 60000, TimeUnit.MILLISECONDS);*/
		 Timer timer = new Timer(); 
	     timer.schedule(new TomcatOnLoadTask(), 1000);
	}

}
