package Flyweight2;

import java.util.HashMap; 

// Class used to get a player using HashMap (Returns an existing player if a player of 
// given type exists. Else creates a new player and returns it. 
public class PlayerFactory { 
	
	/* HashMap stores the reference to the object of Terrorist(TS) or CounterTerrorist(CT). */
	private static HashMap <String, Player> cache = new HashMap<String, Player>(); 

	// Method to get a player 
	public static Player getPlayer(String type) { 
		Player player = null; 

		/* If an object for TS or CT has already been created simply return its reference */
		if (true == cache.containsKey(type)) {
			player = cache.get(type); 
		} else { 
			/* create an object of TS/CT */
			switch(type) 
			{ 
				case "Terrorist": 
					System.out.println("Terrorist Created"); 
					player = new Terrorist(); 
					break; 
				case "CounterTerrorist": 
					System.out.println("Counter Terrorist Created"); 
					player = new CounterTerrorist(); 
					break; 
				default : 
					System.out.println("Unreachable code!"); 
			} 
			
			// Once created insert it into the HashMap 
			cache.put(type, player); 
		} 
		return player; 
	} 
} 