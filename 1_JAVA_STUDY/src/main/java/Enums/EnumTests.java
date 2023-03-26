package Enums;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** Build status: **/
enum BuildStatus {
	/* Undefined state. */
	Undefined,

	/* Build just started. */
	BuildStarted,
	
	/* Installers has been assembled and downleded to FTP. */
	InstallersAssembled,
	
	/* Unit tests run has been triggered. */
	TestsStarted,
	
	/* All tests completed. */
	TestsCompleted;

	/** Converts string to BuildStatus enum type object. **/
	public static BuildStatus fromString(final String enumStr) {
	    try {
	    	return BuildStatus.valueOf(enumStr);
	    } catch (Exception exc) {
	    	System.err.println(exc.getMessage());
	    	return BuildStatus.Undefined;
	    }
	}
}

/************ Months: ************/
enum Months {
	None,
	January,
	February,
	March,
	April,
	May,
	June,
	July,
	August,
	September,
	October,
	November,
	December;
	
	public static Months valueFromString(final String enumStr) {
		Months result = Months.None;
		try {
			result = Months.valueOf(enumStr);
		} catch (Exception exc) {
			System.err.println(exc.getMessage());
		}
		return result;
	}
	
}

/************ Direction: *************/
enum Direction {
	UP,
	DOWN;
	
	public Direction opsite() {
		return this == UP ?  DOWN : UP;
	}
}



/********** MyEnum: **************/
enum MyEnum {
    ENUM_1("A"),
    ENUM_2("B");

    private String name;

    private static final Map<String,MyEnum> ENUM_MAP;

    MyEnum (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Build an immutable map of String name to enum pairs.
    // Any Map impl can be used.

    static {
        Map<String,MyEnum> map = new ConcurrentHashMap<String, MyEnum>();
        for (MyEnum instance : MyEnum.values()) {
            map.put(instance.getName(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static MyEnum get (String name) {
        return ENUM_MAP.get(name);
    }
}

//////////////////////////////////////////////////////////////////////////////////////////

enum Level {
    HIGH(3), 
    MEDIUM(2),
    LOW (1);
	
    private final int levelCode;
    
    Level(int levelCode) {
        this.levelCode = levelCode;
    }
    
    public int getLevelCode() {
        return this.levelCode;
    }
}


enum LevelEx {
    HIGH{ 
        @Override
        public String asLowerCase() {
            return HIGH.toString().toLowerCase();
        }
    },
    MEDIUM {
        @Override
        public String asLowerCase() {
            return MEDIUM.toString().toLowerCase();
        }
    },
    LOW {
        @Override
        public String asLowerCase() {
            return LOW.toString().toLowerCase();
        }
    };

	public abstract String asLowerCase();
}

//////////////////////////////////////////////////////////////////////

enum Monster {
    GOBLIN(100, 50),
    HOBGOBLIN(110, 30),
    GREMLIN(200, 10);
 
    private int health;
    private int magic;
 
    Monster(int health, int magic) {
        this.health = health;
        this.magic = magic;
    }
 
    public int getHealth() {
        return this.health;
    }
 
    public int getMagic() {
        return this.magic;
    }
}

enum MonsterEx {
    GOBLIN {
    	public void doSomething() {
            System.out.println("GOBLIN is doing something");
        }
    	
    	@Override
        public void doWork() {
    		this.doSomething();
        }
    },
    HOBGOBLIN(110, 30) {
    	public void doOtherThing() {
    		System.out.println("HOBGOBLIN is doing something");
        }
 
    	@Override
        public void doWork() {
    		doOtherThing();
        }
    },
    GREMLIN(200, 10) {
    	@Override
        public void doWork() {
    		System.out.println("GREMLIN is doing something without Function");
        }
    };
 
    private int health;
    private int magic;
 
    MonsterEx() {
    }
   
    MonsterEx(int health, int magic) {
        this.setHealth(health);
        this.setMagic(magic);
    }
 
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMagic() {
		return magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}
	
    public abstract void doWork();
}


//////////////////////////////// TESTS ///////////////////////////////
public class EnumTests {
	
	public static void Direction_Test() {
		Direction direction = Direction.DOWN;
		System.out.println(direction);
		System.out.println(direction.opsite());
	}
	
	public static void Months_Test() {
		Months june = Months.June;
		System.out.println(june);
		
		Months month = Months.valueFromString("April2 ");
		System.out.println(month);
		
		Months may = Months.valueFromString("May");
		System.out.println(may);
	}
	
	public static void Level_Enum_Tests() {
		Level level = Level.HIGH;

		System.out.println(level.getLevelCode());
		System.out.println(level.ordinal());
		System.out.println(level.toString());
	}
	
	public static void LevelEx_Enum_Tests() {
		LevelEx level = LevelEx.HIGH;

		System.out.println(level.ordinal());
		System.out.println(level.toString());
		System.out.println(level.toString().toLowerCase());
	}
	
	public static void Monster_Enum_Test() {
		Monster monster = Monster.GOBLIN;
		System.out.println(monster.name() + ". Health: " + monster.getHealth() + " magic: " + monster.getMagic());
		
		monster = Monster.GREMLIN;
		System.out.println(monster.name() + ". Health: " + monster.getHealth() + " magic: " + monster.getMagic());
	}
	
	public static void MonsterEx_Enum_Test() {
		MonsterEx monster = MonsterEx.GOBLIN;
		System.out.println(monster.name() + ". Health: " + monster.getHealth() + " magic: " + monster.getMagic());
		monster.doWork();
		
		monster = MonsterEx.GREMLIN;
		System.out.println(monster.name() + ". Health: " + monster.getHealth() + " magic: " + monster.getMagic());
		monster.doWork();
		
		monster = MonsterEx.HOBGOBLIN;
		System.out.println(monster.name() + ". Health: " + monster.getHealth() + " magic: " + monster.getMagic());
		monster.doWork();
	}
	
	///////////////////////////// MAIN //////////////////////////////
	
	public static void main(String[] args) {
		// Direction_Test();
		// Months_Test();
		
		// Level_Enum_Tests();
		// LevelEx_Enum_Tests();
		
		// Monster_Enum_Test();
		// MonsterEx_Enum_Test();
		
		for (Months m: Months.values())
			System.out.println(m);
	}
}
