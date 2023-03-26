/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Prototype pattern demo class
*
* @name      : PrototypeDemo2.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 17, 2020
* 
****************************************************************************/ 

package Prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface Prototype {
	public Prototype clone();
	
	public String getName();
	
	public void execute();
}

class PrototypeModule {
	private static List<Prototype> prototypes = new ArrayList<>();
	
	public static void addPrototype(Prototype p) {
		prototypes.add(p);
	}

	public static Prototype createPrototype(String name) {
		for (Prototype p : prototypes) {
			if (p.getName().equals(name)) {
				return p.clone();
			}
		}
		System.out.println(name + ": doesn't exist");
		return null;
	}
}

class PrototypeAlpha implements Prototype {
	private String name = "AlphaVersion";

	@Override
	public Prototype clone() {
		return new PrototypeAlpha();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void execute() {
		System.out.println(name + ": does something");
	}
}

class PrototypeBeta implements Prototype {
	private String name = "BetaVersion";

	@Override
	public Prototype clone() {
		return new PrototypeBeta();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void execute() {
		System.out.println(name + ": does something");
	}
}

class ReleasePrototype implements Prototype {
	private String name = "ReleaseCandidate";
	
	@Override
	public Prototype clone() {
		return new ReleasePrototype();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void execute() {
		System.out.println(name + ": does real work");
	}
}

public class PrototypeDemo2 {
	
	public static void main(String[] args) {
		PrototypeModule.addPrototype(new PrototypeAlpha());
		PrototypeModule.addPrototype(new PrototypeBeta());
		PrototypeModule.addPrototype(new ReleasePrototype());

		List<String> params = new ArrayList<String>(Arrays.asList("PrototypeDemo", "Garbage", "AlphaVersion", "BetaVersion", "Nothing", "ReleaseCandidate"));
        List<Prototype> prototypes = new ArrayList<>();
          
        System.out.println("\nCreating protypes:");
        for (String protoName : params) {
        	Prototype prototype = PrototypeModule.createPrototype(protoName);
        	if (prototype != null) {
        		prototypes.add(prototype);
        	}
        }
          
        System.out.println("\nExcute:");
        for (Prototype p : prototypes) {
        	p.execute();
        }
	}
	
}
