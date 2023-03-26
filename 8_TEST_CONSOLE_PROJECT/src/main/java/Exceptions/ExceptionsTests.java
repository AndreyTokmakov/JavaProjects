package Exceptions;

public class ExceptionsTests
{
    private static class BadException extends Exception
    {
        private final Boolean flag = true;

        public BadException(String message){
            super(message);
        }

        @Override
        public String toString()  {
            if (flag)
                throw new RuntimeException("Opppsss");
            return super.toString();
        }
    }

    public void func() throws Exception {
        System.out.println("func() called");
        throw new Exception("Exception from func!!!");
    }

    public void func2() throws Exception {
        System.out.println("func2() called");
        throw new BadException("BadException from func!!!");
    }


    public void TestFinally() {
        try {
            func();
            // System.exit(0);
        } catch (Exception e) {
            System.out.println("Exception !");
            throw new RuntimeException("New RuntimeException!");
        } finally {
            System.out.println("Finally() called");
        }
    }

    public void TestFinally2() {
        try {
            func2();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("Finally() called");
        }
    }



    public static void main(String[] args)
    {
        // new ExceptionsTests().TestFinally();
        new ExceptionsTests().TestFinally2();
    }
}
