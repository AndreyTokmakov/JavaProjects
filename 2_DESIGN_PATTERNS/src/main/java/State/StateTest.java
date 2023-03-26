package State;

interface IState {
    public String getName();
    public void freeze(StateContext context);
    public void heat(StateContext context);
}

abstract class State implements IState {
    final private String name;

    public State(final String name) {
    	this.name = name; 
    }
    
    public String getName() {
        return this.name;
    }
}

class StateContext {
    private IState state = null;
    
    public StateContext(IState state) {
		this.state = state;
	}

    public void freeze() {
        System.out.println("Freezing " + state.getName() + " substance...");
        state.freeze(this);
    }

    public void heat() {
        System.out.println("Heating " + state.getName() + " substance...");
        state.heat(this);
    }

    public void setState(IState state) {
        System.out.println("Changing state to " + state.getName() + "...");
        this.state = state;
    }

    public IState getState() {
        return this.state;
    }
}

class SolidState extends State {

    public SolidState() {
		super("Solid");
	}

	public void freeze(StateContext context) {
        System.out.println("Nothing happens.");
    }

    public void heat(StateContext context) {
        context.setState(new LiquidState());
    }
}

class LiquidState extends State {

    public LiquidState() {
		super("Liquid");
	}

    public void freeze(StateContext context) {
        context.setState(new SolidState());
    }

    public void heat(StateContext context) {
        context.setState(new GaseousState());
    }
}

class GaseousState extends State {

    public GaseousState() {
		super("Gas");
	}

    public void freeze(StateContext context) {
        context.setState(new LiquidState());
    }

    public void heat(StateContext context) {
        System.out.println("Nothing happens.");
    }
}

/////////////////////////////////////////////////////////

public class StateTest {
    public static void main(String[] args) {
        StateContext context = new StateContext(new SolidState());
        context.heat();
        context.heat();
        context.heat();
        context.freeze();
        context.freeze();
        context.freeze();
    }
}