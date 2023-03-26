package Collections.Map;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss : ");
	
	protected static void print(String text) {
		System.out.println(LocalDateTime.now().format(formatter) + text);
	}

	public static void main(String[] args) 
	{
		Map<Integer, String> map = new WeakHashMap<Integer, String>();
        map.put(1, "Proselyte");
        if (true == map.containsKey(1)) {
        	print("Map contains 1");
        }
        
        Runnable runner = new Runnable() {
            @Override
            public void run() {
                while (true == map.containsKey(1)) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    print("Thread is waiting...");
                    System.gc();
                }
            }
        };

        Thread thread = new Thread(runner);
        thread.start();
        print("Application is waiting...");
        try {
            thread.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // System.out.println("Done");
	}
}
