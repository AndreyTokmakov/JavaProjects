package Callback;

interface EventHandler {
    void execute();
}

class ButtonClickHandler implements EventHandler{
    public void execute(){ 
        System.out.println("Button clicked.");
    }
}

class Button{
    private EventHandler handler;
    
    Button(EventHandler action){
        this.handler =action;
    }
    
    public void click(){
        handler.execute();
    }
}

public class ButtonClickTest {
	
	 public static void Test2() {
		Button tvButton = new Button(new EventHandler(){
			private boolean on = false;
			public void execute(){               
				if (on) {
					System.out.println("TV off");
					on = false;
				} else {
					System.out.println("TV on");
					on=true;
				}
			}
		});
	         
		Button printButton = new Button(new EventHandler() {
			public void execute(){
				System.out.println("Printer printing started ...");
			}
		});
	         
		tvButton.click();
		printButton.click();
		tvButton.click();
	}
		
	public static void Test1() {
		Button button = new Button(new ButtonClickHandler());
	    button.click();
	    button.click();
	    button.click();
	}
	 
	public static void main(String[] args) {
		// Test1();
		Test2();
	}
}
