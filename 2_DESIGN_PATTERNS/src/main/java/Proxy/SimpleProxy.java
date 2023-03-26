package Proxy;

interface RealObject {
    void doSomething();
}

class RealObjectImpl implements RealObject {
    @Override
    public void doSomething() {
        System.out.println("Performing work in real object");
    }
}

class RealObjectProxy extends RealObjectImpl {
    private final RealObject impl;

    public RealObjectProxy(RealObject objImpl)  {
        this.impl = objImpl;
    }

    @Override
    public void doSomething() {
        // Perform additional logic and security: Even we can block the operation execution
        impl.doSomething();
    }
}

public class SimpleProxy
{
    public static void main(String[] args)
    {
        RealObject proxy = new RealObjectProxy(new RealObjectImpl());
        proxy.doSomething();
    }
}
