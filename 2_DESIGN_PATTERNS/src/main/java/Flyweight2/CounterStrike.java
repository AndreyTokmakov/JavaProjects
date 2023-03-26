package Flyweight2;

import java.util.Random; 

//Driver class 	
public class CounterStrike  { 
	
	// All player types and weapon (used by getRandPlayerType() and getRandWeapon()
	private static String[] playerType = {"Terrorist", "CounterTerrorist"}; 
	private static String[] weapons    = {"AK-47", "Maverick", "Gut Knife", "Desert Eagle"}; 
	private final static Random rand = new Random();

	// Utility methods to get a random player type and weapon 
	public static String getRandPlayerType()  { 
		// Will return an integer between [0,2) 
		int randInt = rand.nextInt(playerType.length); 

		// return the player stored at index 'randInt' 
		return playerType[randInt]; 
	} 
	
	public static String getRandWeapon()  { 
		// Will return an integer between [0,5) 
		int randInt = rand.nextInt(weapons.length); 

		// Return the weapon stored at index 'randInt' 
		return weapons[randInt]; 
	} 
	
	
	// Driver code 
	public static void main(String args[])  { 
		/* Assume that we have a total of 10 players in the game. */
		
		for (int i = 0; i < 10; i++)  { 
			/* getPlayer() is called simply using the class name since the method is a static one */
			Player player = PlayerFactory.getPlayer(getRandPlayerType()); 

			/* Assign a weapon chosen randomly uniformly from the weapon array */
			player.assignWeapon(getRandWeapon()); 

			// Send this player on a mission 
			player.mission(); 
		} 
	} 
} 
