import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class RateLimiterLongRunningUnitTest
{
    /** We’ll create a RateLimiter with 100 permits.
     * Then we’ll execute an action that needs to acquire 1000 permits.
     * According to the specification of the RateLimiter, such action will need at least 10 seconds
     * to complete because we’re able to execute only 100 units of action per second:
    **/
    @Test
    public void givenLimitedResource_whenUseRateLimiter_thenShouldLimitPermits() {
        //given
        RateLimiter rateLimiter = RateLimiter.create(100);

        //when
        long startTime = ZonedDateTime.now().getSecond();
        IntStream.range(0, 1000).forEach(i -> {
            rateLimiter.acquire();
            doSomeLimitedOperation();
        });
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;

        //then
        assertThat(elapsedTimeSeconds >= 10);
    }

    @Test
    public void givenLimitedResource_whenRequestTwice_thenShouldPermitWithoutBlocking() {
        //given
        RateLimiter rateLimiter = RateLimiter.create(2);

        //when
        long startTime = ZonedDateTime.now().getSecond();
        rateLimiter.acquire(1);
        doSomeLimitedOperation();
        rateLimiter.acquire(1);
        doSomeLimitedOperation();
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;

        //then
        assertThat(elapsedTimeSeconds <= 1);
    }

    @Test
    public void givenLimitedResource_whenRequestOnce_thenShouldPermitWithoutBlocking() {
        //given
        RateLimiter rateLimiter = RateLimiter.create(100);

        //when
        long startTime = ZonedDateTime.now().getSecond();
        rateLimiter.acquire(100);
        doSomeLimitedOperation();
        long elapsedTimeSeconds = ZonedDateTime.now().getSecond() - startTime;

        //then
        assertThat(elapsedTimeSeconds <= 1);
    }

    /**
     * The RateLimiter API has also a very useful acquire() method that accepts a timeout and TimeUnit as arguments.
     * Calling this method when there are no available permits will cause it to wait for
     * specified time and then time out – if there are not enough available permits within the timeout.
     * When there are no available permits within the given timeout, it returns false.
     * If an acquire() succeeds, it returns true:**/
    @Test
    public void givenLimitedResource_whenTryAcquire_shouldNotBlockIndefinitely() {
        //given
        RateLimiter rateLimiter = RateLimiter.create(1);

        //when
        rateLimiter.acquire();
        boolean result = rateLimiter.tryAcquire(2, 10, TimeUnit.MILLISECONDS);

        //then
        assertThat(result).isFalse();

    }

    private void doSomeLimitedOperation() {
        //some computing
    }

}
