package Object_Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

class DataStorage {
    private String packet;
    // True if receiver should wait
    // False if sender should wait
    private volatile boolean transfer = true;
  
    public synchronized void put(String packet) {
        while (false == this.transfer) {
            try { 
            	this.wait();
            } catch (InterruptedException exc)  {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread interrupted: " + exc); 
            }
        }
        this.transfer = false;
        this.packet = packet;
        this.notifyAll();
    }
  
    public synchronized String get() {
        while (true == this.transfer) {
            try {
            	this.wait();
            } catch (InterruptedException exc)  {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread interrupted: " + exc); 
            }
        }
        this.transfer = true;
        this.notifyAll();
        return this.packet;
    }
}

class Sender implements Runnable {
    private DataStorage data;
    protected ArrayList<String> packets = new ArrayList<String>();
    
    public Sender(final DataStorage storage) {
    	this.data = storage;
    	this.packets.addAll(Arrays.asList("First packet", "Second packet","Third packet","Fourth packet","End"));
    }
  
    public void run() {
        for (final String packet : packets) {
            data.put(packet);
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException exc)  {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread interrupted: " + exc); 
            }
        }
    }
}

class Receiver implements Runnable {
    private DataStorage data;
    
    public Receiver(final DataStorage storage) {
    	this.data = storage;
    }
  
    public void run() {
    	String receivedMessage = "";
    	while (true) {
            try {
        		receivedMessage = data.get();
        		if (true == receivedMessage.equals("End")) 
        			break;
        		System.out.println(receivedMessage);
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException exc) {
                Thread.currentThread().interrupt(); 
                System.err.println("Thread interrupted: " + exc); 
            }
    	}
    }
}

public class Wait_Test2 {
	public static void main(String[] args) {
		DataStorage dataStorage = new DataStorage();
		Receiver receiver = new Receiver(dataStorage);
		Sender sender = new Sender(dataStorage);
        new Thread(receiver).start();
        new Thread(sender).start();
	}
}
