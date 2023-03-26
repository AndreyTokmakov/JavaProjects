/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Cleaner tests
*
* @name    : Cleaner_Tests.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : November 30, 2020
****************************************************************************/ 

package Memory;

import java.lang.ref.Cleaner;

// An AutoCloseable class using a cleaner as a safety net
class Room implements AutoCloseable {
	private static final Cleaner cleaner = Cleaner.create();
	
	/** The state of this room, shared with our cleanable. **/
	private final State state;
	
	/** Our cleanable. Cleans the room when eligible for gc. **/
	private final Cleaner.Cleanable cleanable;
	
	public Room(int numJunkPiles) {
		state = new State(numJunkPiles);
		cleanable = cleaner.register(this, state);
	}
	
	@Override 
	public void close() {
		cleanable.clean();
	}
	
	// Resource that requires cleaning. Must not refer to Room!
	private static class State implements Runnable {
		// Number of junk piles in this room
		int numJunkPiles; 
		
		State(int numJunkPiles) {
			this.numJunkPiles = numJunkPiles;
		}
		
		//Invoked by close method or cleaner
		@Override 
		public void run() {
			System.out.println("Cleaning room");
			numJunkPiles = 0;
		}
	}
}



public class Cleaner_Tests {
	
	public static void Good_Test() {
		try (Room myRoom = new Room(7)) {
			System.out.println("Goodbye");
		}
	}
	
	public static void Bad_Test() {
		@SuppressWarnings({ "unused", "resource" })
		Room room = new Room(99);
		System.out.println("Peace out");
	}
	
	public static void main(String[] args) {
		Good_Test();
		// Bad_Test();
	}
}
