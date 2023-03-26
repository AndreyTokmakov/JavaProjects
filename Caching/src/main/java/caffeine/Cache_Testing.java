package caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import lombok.Data;

import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;

@Data
class DataObject {
    private final String data;

    private static int objectCounter = 0;

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }
}


class CacheTester {
	
	public void EmptyCacheTest() {
		Cache<String, DataObject> cache = Caffeine.newBuilder()
				  .expireAfterWrite(1, TimeUnit.MINUTES)
				  .maximumSize(100)
				  .build();
		
		String key = "A";
		DataObject dataObject = cache.getIfPresent(key);

		// Assert.assertNull(dataObject);
		Assertions.assertThat(dataObject).isNull();

		System.out.println("dataObject = " + dataObject);
	}
	
	public void Get_If_Present() {
		Cache<String, DataObject> cache = Caffeine.newBuilder()
				  .expireAfterWrite(1, TimeUnit.MINUTES)
				  .maximumSize(100)
				  .build();
		
		String key = "One";
		
		DataObject dataObject1 = cache.getIfPresent(key);
		System.out.println("1. data = " + dataObject1);
		
		DataObject dataObject2 = cache.get(key, k -> DataObject.get(key));
		System.out.println("1. data = " + dataObject2);
		
		DataObject dataObject3 = cache.getIfPresent(key);
		System.out.println("3. data = " + dataObject3);
	}
	
	
	public void Objects_LifeTime_Expire() {
		Cache<String, DataObject> cache = Caffeine.newBuilder()
				  .expireAfterWrite(5, TimeUnit.SECONDS)
				  .maximumSize(100)
				  .build();
		
		final String key = "One";

		DataObject dataObject = cache.get(key, k -> DataObject.get(key));
		System.out.println("1. data = " + dataObject);
		
		System.out.println("Access cached value loop:");
		for (int i = 0; i < 10; i++) {
			dataObject = cache.getIfPresent(key);
			System.out.println("data = " + dataObject);
			sleep(1000);
		}
	}
	
	public void Objects_LifeTime_Expire2() {
		Cache<String, DataObject> cache = Caffeine.newBuilder()
				  .expireAfterWrite(5, TimeUnit.SECONDS)
				  .maximumSize(100)
				  .build();
		
		final String key = "One";
		cache.put(key, new DataObject(key));

		System.out.println("Access cached value loop:");
		for (int i = 0; i < 10; i++) {
			System.out.println("data = " + cache.getIfPresent(key));
			sleep(1000);
		}
	}
	
	public void AddOneEntry() {
		Cache<String, DataObject> cache = Caffeine.newBuilder()
				  .expireAfterWrite(1, TimeUnit.MINUTES)
				  .maximumSize(100)
				  .build();
		
		String key = "A";
		DataObject dataObject = cache.get(key, k -> DataObject.get("Data for A"));

		// Assert.assertNotNull(dataObject);
		Assertions.assertThat(dataObject).isNotNull();
		System.out.println("dataObject = " + dataObject);
		
		// Assert.assertEquals("Data for A", dataObject.getData());
		Assertions.assertThat(dataObject.getData().equals("Data for A")).isTrue();

		System.out.println("getData = " + dataObject.getData());
	}
	
	
	private void sleep(int msTimeout) {
		try {
			Thread.currentThread();
			Thread.sleep(msTimeout);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}
}


public class Cache_Testing {
	/** **/
	private final static CacheTester tester = new CacheTester();
	
	public static void main(String[] args) {
		
		// tester.EmptyCacheTest();
		// tester.AddOneEntry();
		 tester.Get_If_Present();
		
		// tester.Objects_LifeTime_Expire();
		// tester.Objects_LifeTime_Expire2();

	}
}
