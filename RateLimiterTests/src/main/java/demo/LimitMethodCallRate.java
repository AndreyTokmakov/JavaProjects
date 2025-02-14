package demo;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalDateTime;

public class LimitMethodCallRate
{
    private static void sleep(final long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

    private final static class Service
    {
        RateLimiter rateLimiter = RateLimiter.create(10);

        public void callSomeEndpoint()
        {
            rateLimiter.acquire(1);
            System.out.println("TestService::callSomeEndpoint() called at" + LocalDateTime.now());
        }
    }

    public static void main(String[] args)
    {
        Service service = new Service();
        for (int i = 0; i < 1000; ++i) {
            service.callSomeEndpoint();
            sleep(10);
        }
    }
}
