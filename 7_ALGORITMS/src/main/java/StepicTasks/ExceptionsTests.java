package StepicTasks;

class Test
{
    public void anotherMethodOne() {
        anotherMethod();
    }

    public void anotherMethod() {
        System.out.println(getCallerClassAndMethodName());
    }

    public String getCallerClassAndMethodName() {
        final StackTraceElement[] methods = Thread.currentThread().getStackTrace();

        final StackTraceElement element = methods[2];
        if (element.getMethodName().equals("main"))
            return null;

        System.out.println(element.getClassName());
        System.out.println(element.getMethodName());
        System.out.println(element.getFileName());
        System.out.println(element.getLineNumber());


        /*
        System.out.println(methods.length);
        for (StackTraceElement element: methods ) {
            System.out.println(element);
        }*/
        return "";
    }
}

public class ExceptionsTests {
    public static void main(String[] args)
    {
        Test test = new Test();



        test.anotherMethodOne();
        test.anotherMethod();

        test.getCallerClassAndMethodName();

    }
}
