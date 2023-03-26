/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* ConcurrentHashMap Java collection demo class
*
* @name      : ConcurrentHashMap_Tests.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : November 03, 2020
****************************************************************************/ 

package Map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Worker {
	
	public  void Create() {
		Map<String, String> dict = new ConcurrentHashMap<String, String>(Map.of("Delhi", "24", "Mumbai", "32", "Chennai", "35", "Bangalore", "22"));
		
		for (Map.Entry<String, String> e : dict.entrySet()) {
		  System.out.println(e.getKey() + " = " + e.getValue());
		}
	}
}

public class ConcurrentHashMap_Tests {
	public static void main(String[] args) {
		Worker worker = new Worker();
		worker.Create();
	}
}
