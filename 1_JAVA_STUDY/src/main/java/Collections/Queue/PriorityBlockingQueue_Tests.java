/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Observer pattern demo class
*
* @name      : PriorityBlockingQueue_Tests.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : November 02, 2020
* 
****************************************************************************/ 

package Collections.Queue;

import java.time.LocalDate;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

class Employee implements Comparable<Employee> {
    private Long id;
    private String name;
    private LocalDate dob;
 
    public Employee(Long id, String name, LocalDate dob) {
        super();
        this.id = id;
        this.name = name;
        this.dob = dob;
    }
     
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@Override
    public int compareTo(Employee emp) {
        return this.getId().compareTo(emp.getId());
    }
 
    //Getters and setters
 
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", dob=" + dob + "]";
    }
}

public class PriorityBlockingQueue_Tests {
	   public static void main(String[] args) throws InterruptedException 
	    {
	        PriorityBlockingQueue<Integer> priorityBlockingQueue = new PriorityBlockingQueue<>();
	         
	        new Thread(() -> 
	        {
	          System.out.println("Waiting to poll ...");
	          
	          try
	          {
	              while(true) 
	              {
	                  Integer poll = priorityBlockingQueue.take();
	                  System.out.println("Polled : " + poll);
	 
	                  Thread.sleep(TimeUnit.SECONDS.toMillis(1));
	              }
	               
	          } catch (InterruptedException e) {
	              e.printStackTrace();
	          }
	           
	        }).start();
	          
	        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
	        priorityBlockingQueue.add(1);
	         
	        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
	        priorityBlockingQueue.add(2);
	         
	        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
	        priorityBlockingQueue.add(3);
	    }
}
