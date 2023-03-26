package Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHeaders;

import com.google.gson.Gson;

class Tests1 {
	private final static Gson gson = new Gson();
	
	public void Test1() {
		List<Object> collection = new ArrayList<Object>();
		collection.add("string");
		collection.add(10);
		//collection.add(new Entity(11, "text"));
		
		System.out.println(gson.toJson(collection));
	}
	
	public void Map_To_Json() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("USD", 123);
		map.put("EUR", 321);

		String json = gson.toJson(map);
		System.out.println(json);
	}
	
	//---------------------------------------------------------------------------------------//
	
	public void Json_for_WireMock() {
        
        Map<String, Object> json = new LinkedHashMap<>();
        json.put("request", Map.of("method", "GET", "url", "/stubs/timeout"));

        Map<Object, Object> headers = Map.ofEntries(
        	Map.entry(HttpHeaders.CONTENT_TYPE, "text/plain")
        );
        json.put("response", Map.of("body", "Hello world!", "fixedDelayMilliseconds", 15000, "status", 200, "headers", headers));

        System.out.println(gson.toJson(json));
	}
}

public class CreateJson {
	private final static Tests1 tests = new Tests1();

	public static void main(String[] args) {
		// tests.Test1();
		// tests.Map_To_Json();
		
		tests.Json_for_WireMock();
	}
}
