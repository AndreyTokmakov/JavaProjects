package Memento;

import java.util.ArrayList;
import java.util.List;

/*
class Memento {
	private final String state;

	public Memento(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}
}
*/

record Memento(String state) {
}

/*
class CareTaker {
	private final List<Memento> mementoList = new ArrayList<Memento>();

	public void add(Memento memento){
		mementoList.add(memento);
	}

	public Memento get(int index){
		return mementoList.get(index);
	}
}*/

class Originator {
	private String state;

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public Memento saveStateToMemento(){
		System.out.println("Saving state: " + state);
		return new Memento(state);
	}

	public void getStateFromMemento(Memento memento){
		state = memento.state();
	}
}

public class MementoPatternDemo
{
	public static void main(String[] args)
	{
		Originator originator = new Originator();

		// CareTaker  careTaker = new CareTaker();
		final List<Memento> mementos = new ArrayList<Memento>();

		originator.setState("State #1");
		originator.setState("State #2");
		// careTaker.add(originator.saveStateToMemento());
		mementos.add(originator.saveStateToMemento());

		originator.setState("State #3");
		// careTaker.add(originator.saveStateToMemento());
		mementos.add(originator.saveStateToMemento());

		originator.setState("State #4");
		System.out.println("Current State: " + originator.getState());

		// originator.getStateFromMemento(careTaker.get(0));
		originator.getStateFromMemento(mementos.get(0));

		System.out.println("First saved State: " + originator.getState());

		// originator.getStateFromMemento(careTaker.get(1));
		originator.getStateFromMemento(mementos.get(1));
		System.out.println("Second saved State: " + originator.getState());
	}
}
