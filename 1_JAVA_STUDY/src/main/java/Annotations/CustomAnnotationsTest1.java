package Annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface JavaFileInfo {
	String author() default "unknown";
	String version() default "0.0";
}

@JavaFileInfo(author = "OnClassLevel")
class DemoClass {
	@JavaFileInfo(author = "Lokesh", version = "1.0")
	public String getString() { 
		return null;
	}
}


public class CustomAnnotationsTest1 {

	static void readAnnotationOn(AnnotatedElement element) {
		try {
			System.out.println("\nFinding annotations on " + element.getClass().getName());
			Annotation[] annotations = element.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof final JavaFileInfo fileInfo) {
					System.out.println("  Author : " + fileInfo.author());
					System.out.println("  Version: " + fileInfo.version());
				}
			}
		} catch (final Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Class<DemoClass> demoClassObj = DemoClass.class;

		readAnnotationOn(demoClassObj);

		Method method = demoClassObj.getMethod("getString");
		readAnnotationOn(method);
	}
}
