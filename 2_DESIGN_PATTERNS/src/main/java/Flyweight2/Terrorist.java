package Flyweight2;

// Terrorist must have weapon and mission 
public class Terrorist implements Player { 
    // Intrinsic Attribute 
    private final String TASK; 
  
    // Extrinsic Attribute 
    private String weapon; 
  
    public Terrorist() { 
    	System.out.println("* * * Creating a new 'CounterTerrorist' Object * * *");
        TASK = "PLANT A BOMB"; 
    } 

    @Override
    public void assignWeapon(final String weapon) { 
        this.weapon = weapon; 
    } 

    @Override
    public void mission()  { 
        System.out.println(String.format("Terrorist with weapon %s | Task is %s", weapon, TASK)); 
    } 
} 