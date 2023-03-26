package Flyweight2;

//CounterTerrorist must have weapon and mission 
public class CounterTerrorist implements Player { 
	// Intrinsic Attribute 
	private final String TASK; 

	// Extrinsic Attribute 
	private String weapon; 

	public CounterTerrorist()  { 
		System.out.println("* * * Creating a new 'CounterTerrorist' Object * * *");
		TASK = "DIFFUSE BOMB"; 
	} 
	
    @Override
    public void assignWeapon(final String weapon) { 
        this.weapon = weapon; 
    } 
	
	@Override
	public void mission() { 
        System.out.println(String.format("Counter Terrorist with weapon %s | Task is %s", weapon, TASK)); 
    } 
} 