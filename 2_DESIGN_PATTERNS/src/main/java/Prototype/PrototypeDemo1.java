/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Prototype pattern demo class
*
* @name      : PrototypeDemo1.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 17, 2020
* 
****************************************************************************/ 

package Prototype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Person {
    public Person clone();
}

class Tom implements Person {
    private final static String NAME = "Tom";

    @Override
    public Person clone() {
        return new Tom();
    }

    @Override
    public String toString() {
        return NAME;
    }
}

class Dick implements Person {
    private final static String NAME = "Dick";

    @Override
    public Person clone() {
        return new Dick();
    }

    @Override
    public String toString() {
        return NAME;
    }
}

class Harry implements Person {
    private final static String NAME = "Harry";

    @Override
    public Person clone() {
        return new Harry();
    }

    @Override
    public String toString() {
        return NAME;
    }
}

class Factory {
    private static final Map<String, Person> prototypes = new HashMap<String, Person>();

    static {
        prototypes.put("tom", new Tom());
        prototypes.put("dick", new Dick());
        prototypes.put("harry", new Harry());
    }

    public static Person getPrototype(String type) {
        try {
            return prototypes.get(type).clone();
        } catch (NullPointerException ex) {
            System.out.println("Prototype with name: " + type + ", doesn't exist");
            return null;
        }
    }
}


public class PrototypeDemo1 {
	public static void main(String[] args) {
		List<String> params = new ArrayList<String>(Arrays.asList("tom", "dick", "jack"));

		for (String type : params) {
			Person prototype = Factory.getPrototype(type);
			if (prototype != null) {
				System.out.println(prototype);
			}
		}
	}
}
