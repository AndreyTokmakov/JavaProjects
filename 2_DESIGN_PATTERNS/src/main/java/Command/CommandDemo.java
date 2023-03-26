package Command;


// An interface for command 
interface Command  {
	public void execute();
} 

// Light class and its corresponding command  classes 
class Light { 
	public void on() { 
		System.out.println("Light is on"); 
	} 
	
	public void off() { 
		System.out.println("Light is off"); 
	} 
} 

class LightOnCommand implements Command { 
	private final Light light;

	// The constructor is passed the light it is going to control. 
	public LightOnCommand(Light light) { 
		this.light = light; 
	} 
	
	@Override
	public void execute() { 
		light.on(); 
	} 
} 

class LightOffCommand implements Command { 
	private final Light light;
	
	public LightOffCommand(Light light) { 
		this.light = light; 
	} 
	
	@Override
	public void execute() { 
		light.off(); 
	} 
} 

//Stereo and its command classes 
class Stereo { 
	public void on() { 
		System.out.println("Stereo is on"); 
	} 
	
	public void off() { 
		System.out.println("Stereo is off"); 
	} 
	
	public void setCD() { 
		System.out.println("Stereo is set for CD input"); 
	} 
	
	public void setDVD() { 
		System.out.println("Stereo is set  for DVD input"); 
	} 
	
	public void setRadio() { 
		System.out.println("Stereo is set for Radio"); 
	} 
	
	public void setVolume(int volume) { 
		System.out.println("Stereo volume set to " + volume); 
	} 
} 

class StereoOffCommand implements Command { 
	private Stereo stereo; 
	
	public StereoOffCommand(Stereo stereo) { 
		this.stereo = stereo; 
	} 
	
	@Override
	public void execute()  { 
		stereo.off(); 
	} 
} 

class StereoOnWithCDCommand implements Command { 
	private Stereo stereo; 
	
	public StereoOnWithCDCommand(Stereo stereo)  { 
		this.stereo = stereo; 
	} 
	
	@Override
	public void execute() { 
		stereo.on(); 
		stereo.setCD(); 
		stereo.setVolume(11); 
	} 
} 

//A Simple remote control with one button 
class SimpleRemoteControl  { 
	private Command cmd; 

	// set the command the remote will execute 
	public void setCommand(Command command) { 
		cmd = command; 
	} 

	public void ExecuteCommand() { 
		cmd.execute(); 
	} 
} 

// Driver class 
public class CommandDemo {

	public static void main(String[] args) 
	{ 
		SimpleRemoteControl remote = new SimpleRemoteControl(); 
		Light light = new Light(); 
		Stereo stereo = new Stereo(); 

		// we can change command dynamically 
		remote.setCommand(new LightOnCommand(light)); 
		remote.ExecuteCommand(); 
		remote.setCommand(new StereoOnWithCDCommand(stereo)); 
		remote.ExecuteCommand(); 
		remote.setCommand(new StereoOffCommand(stereo)); 
		remote.ExecuteCommand(); 
	} 
}
