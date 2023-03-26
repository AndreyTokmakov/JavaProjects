package Mediator;

interface Command { 
	public void land(); 
} 

interface Mediator { 
	public void registerRunway(Runway runway);

	public void registerFlight(Flight flight);

	public void setLandingStatus(boolean status);

	public boolean isLandingOk(); 
} 

class ATCMediator implements Mediator {
	private Runway runway;
	public boolean land = false;

	@Override
	public void registerRunway(Runway runway) {
		this.runway = runway;
	}

	@Override
	public void registerFlight(Flight flight) {
	}

	@Override
	public boolean isLandingOk() { 
		// System.out.println("Return " + land);
		return land; 
	} 

	@Override
	public void setLandingStatus(boolean status) { 
		land = status; 
	} 
} 


class Flight implements Command  { 
	private final Mediator mediator;

	public Flight(Mediator mediator) { 
		this.mediator = mediator; 
	} 

	@Override
	public void land() { 
		if (mediator.isLandingOk()) { 
			System.out.println("Successfully Landed."); 
			mediator.setLandingStatus(true); 
		} 
		else
			System.out.println("Waiting for landing."); 
	} 

	public void getReady() { 
		System.out.println("Ready for landing."); 
	} 
} 

class Runway implements Command { 
	private final Mediator mediator;

	public Runway(Mediator mediator) { 
		this.mediator = mediator; 
		mediator.setLandingStatus(true);
	} 

	@Override
	public void land() { 
		System.out.println("Landing permission granted."); 
		this.mediator.setLandingStatus(true); 
	} 

} 


public class MediatorTest
{
	public static void main(String[] args)
	{
		Mediator mediator = new ATCMediator(); 
		
		Flight sparrow101 = new Flight(mediator); 
		Runway mainRunway = new Runway(mediator); 
		
		// mediator.registerFlight(sparrow101);
		// mediator.registerRunway(mainRunway);
		
		sparrow101.land(); 


		sparrow101.getReady(); 
		mainRunway.land(); 
		sparrow101.land();

	}
}
