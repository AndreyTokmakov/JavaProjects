/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Timer_Tests class
*
* @name    : DurationExample.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Timer_Tests 21, 2020
****************************************************************************/ 

package Date_Time_Duration;

import java.util.Date;
import java.util.TimerTask;

class Helper extends TimerTask  { 
    public int count = 0; 
    
    @Override
    public void run() { 
        System.out.println("Timer ran " + (++count)); 
    } 
}

class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("Timer task started at: " + new Date());
        completeTask();
        System.out.println("Timer task finished at: "+ new Date());
    }

    private void completeTask() {
        try {
        	Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TimerTestsWorker {
	/** Time instance: **/
	private java.util.Timer timer = new java.util.Timer();
	
	private void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	protected void Schedule() {
		TimerTask task = new Helper(); 
        timer.schedule(task, 2000, 5000); 
	}
	
    public void ScheduleAtFixedRate() {
        TimerTask timerTask = new MyTimerTask();
        timer.scheduleAtFixedRate(timerTask, 0, 10*1000);
        
        System.out.println("TimerTask started.");
        sleep(120000);
        
        timer.cancel();
        
        System.out.println("TimerTask cancelled.");
        sleep(30000);
    }
}

public class Timer_Tests {
	private final static TimerTestsWorker tests = new TimerTestsWorker();
	
	public static void main(String[] args) {
		// tests.Schedule();
		tests.ScheduleAtFixedRate();
	}
}
