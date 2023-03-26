package Annotations;

import java.lang.annotation.ElementType; 
import java.lang.annotation.Target; 
import java.lang.annotation.Repeatable; 
import java.lang.annotation.Retention; 
import java.lang.annotation.RetentionPolicy; 
import java.lang.reflect.Method; 
import java.lang.annotation.Annotation; 

// Using target annotation to annotate a type 
@Target(ElementType.TYPE_USE) 
// Declaring a simple type annotation 
@interface TypeAnnoDemo{ 
	
} 

// Make Words annotation repeatable 
@Retention(RetentionPolicy.RUNTIME) 
@Repeatable(MyRepeatedAnnos.class) 
@interface Words{ 
	String word() default "Hello"; 
	int value() default 0; 
} 

// Create container annotation 
@Retention(RetentionPolicy.RUNTIME) 
@interface MyRepeatedAnnos{ 
	Words[] value(); 
} 



class Tests { 
	public void Test1() { 
	     // Annotating the type of a string 
	     @TypeAnnoDemo String string = "I am annotated with a type annotation"; 
	     System.out.println(string); 
	     abc(); 
	} 
	
	// Annotating return type of a function 
	static @TypeAnnoDemo int abc(){ 
		System.out.println("This function's  return type is annotated"); 
		return 0; 
	} 
	
	// Repeat Words on newMethod 
    @Words(word = "First", value = 1) 
    @Words(word = "Second", value = 2) 
    public void newMethod(){ 
    	Tests obj = new Tests(); 
        try { 
            Class<?> c = obj.getClass(); 
  
            // Obtain the annotation for newMethod 
            Method m = c.getMethod("newMethod"); 
              
            // Display the repeated annotation 
            Annotation anno = m.getAnnotation(MyRepeatedAnnos.class); 
            System.out.println(anno); 
        } catch (NoSuchMethodException e){ 
            System.out.println(e); 
        } 
    } 
    
	public void Test2() { 
		newMethod();
	} 
} 



public class AnnotationsTests {
	public static void main(String[] args) {
		Tests t = new Tests();
		
		// t.Test1();
		t.Test2();
	}
}
