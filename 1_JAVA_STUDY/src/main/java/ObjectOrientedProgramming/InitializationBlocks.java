package ObjectOrientedProgramming;

class Test_OneBlock  { 
    // Instance Initialization Block 
    {  
        System.out.println("Test_OneBlock Initialization Block 1"); 
    }
     
    public Test_OneBlock()   { 
        System.out.println("Test_OneBlock class Constructor called"); 
    } 
} 


class Test_MultipleBlock  { 
    // Instance Initialization Block 
    {  
        System.out.println("Initialization Block 1"); 
    }
    
    {  
        System.out.println("Initialization Block 2"); 
    } 
    
    public Test_MultipleBlock()   { 
        System.out.println("Test_MultipleBlock class Constructor called"); 
    } 
     
    {  
        System.out.println("Initialization Block 3"); 
    } 
} 

class Derived_OneBlock extends Test_OneBlock { 
    // Instance Initialization Block 
    {  
        System.out.println("Derived_OneBlock Initialization block"); 
    } 
     
    public Derived_OneBlock()   { 
        System.out.println("Derived_OneBlock class Constructor called"); 
    } 
} 

class Test_StaticBlock {
    static int staticVariable;
    int nonStaticVariable;        

    // Static initialization block:
    // Runs once (when the class is initialized)
    static {  
        System.out.println("Test_StaticBlock STATIC Initialization block. WILL BE CALLES ONLY ONCE FOR THIS CLASS TYPE "); 
        staticVariable = 5;
    }
    

    static {  
        System.out.println("Test_StaticBlock STATIC Initialization block 2. WILL BE CALLES ONLY ONCE FOR THIS CLASS TYPE "); 
        staticVariable = 5;
    }

    // Instance Initialization Block 
    {  
        System.out.println("Test_StaticBlock Initialization block"); 
        nonStaticVariable = 7;
    }

    public Test_StaticBlock()   { 
        System.out.println("Derived_OneBlock class Constructor called"); 
    } 
} 

class DerivedTest_StaticBlock extends Test_StaticBlock{

    // Static initialization block:
    // Runs once (when the class is initialized)
    static {  
        System.out.println("DerivedTest_StaticBlock STATIC Initialization block. WILL BE CALLES ONLY ONCE FOR THIS CLASS TYPE "); 
    }

    // Instance Initialization Block 
    {  
        System.out.println("DerivedTest_StaticBlock Initialization block"); 
    }

    public DerivedTest_StaticBlock()   { 
        System.out.println("DerivedTest_StaticBlock class Constructor called"); 
    } 
} 


public class InitializationBlocks {
	public static void main(String[] args)
    {
		// Test_OneBlock obj1 = new Test_OneBlock();
		// Test_MultipleBlock obj1 = new Test_MultipleBlock();
		// Derived_OneBlock obj1 = new Derived_OneBlock();
		
		Test_StaticBlock obj1 = new Test_StaticBlock();
		// Test_StaticBlock obj2 = new Test_StaticBlock();
		
		// DerivedTest_StaticBlock obj1 = new DerivedTest_StaticBlock();
	}
}
