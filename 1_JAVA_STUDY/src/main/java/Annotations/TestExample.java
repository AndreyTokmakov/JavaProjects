package Annotations;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

enum Priority { 
	LOW, 
	MEDIUM, 
	HIGH 
};

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
@interface Test {
	//should ignore this test?
	public boolean enabled() default true;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
@interface TesterInfo {
	Priority priority() default Priority.MEDIUM;
	String[] tags() default "";
	String createdBy() default "AndTokm";
	String lastModified() default "03/01/2014";
}


@TesterInfo(
	priority = Priority.HIGH,
	createdBy = "AndTokm_the_great_hacker",
	tags = {"sales","test" }
)
class TheTestExampleClass {
	@Test
	void testA() {
		if (true)
			throw new RuntimeException("This test always failed");
	}

	@SuppressWarnings("unused")
	@Test(enabled = false)
	void testB() {
		if (false)
			throw new RuntimeException("This test always passed");
	}

	@Test(enabled = true)
	void testC() {
		if (10 > 1) {
			// do nothing, this test always passed.
		}
	}
}

public class TestExample
{
	public static void main(String[] args)
	{
		System.out.println("Running tests:");
		int passed = 0, failed = 0, count = 0, ignore = 0;
		final Class<TheTestExampleClass> obj = TheTestExampleClass.class;

		// Process @TesterInfo
		if (obj.isAnnotationPresent(TesterInfo.class)) {
			final Annotation annotation = obj.getAnnotation(TesterInfo.class);
			TesterInfo testerInfo = (TesterInfo) annotation;
			System.out.printf("%nPriority :%s", testerInfo.priority());
			System.out.printf("%nCreatedBy :%s", testerInfo.createdBy());
			System.out.printf("%nTags :");

			int tagLength = testerInfo.tags().length;
			for (String tag : testerInfo.tags()) {
				if (tagLength > 1) {
					System.out.print(tag + ", ");
				} else {
					System.out.print(tag);
				}
				tagLength--;
			}
			System.out.printf("%nLastModified :%s%n%n", testerInfo.lastModified());
		}

		// Process @Test
		for (Method method : obj.getDeclaredMethods()) {
			// if method is annotated with @Test
			if (method.isAnnotationPresent(Test.class)) {
				Annotation annotation = method.getAnnotation(Test.class);
				Test test = (Test) annotation;
				// if enabled = true (default)
				if (test.enabled()) {
				  try {
					method.invoke(obj.newInstance());
					System.out.printf("%s - Test '%s' - passed %n", ++count, method.getName());
					passed++;
				  } catch (Throwable ex) {
					System.out.printf("%s - Test '%s' - failed: %s %n", ++count, method.getName(), ex.getCause());
					failed++;
				  }
				} else {
					System.out.printf("%s - Test '%s' - ignored%n", ++count, method.getName());
					ignore++;
				}

			}

		}
		System.out.printf("%nResult : Total : %d, Passed: %d, Failed %d, Ignore %d%n", count, passed, failed, ignore);
	}
}
