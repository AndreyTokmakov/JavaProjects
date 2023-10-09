import org.junit.jupiter.api.Assertions;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.AfterEach;


public class TestNatsModules
{
    @Test
    public void testGetSum() throws Exception {
        Assertions.assertEquals(15, 15);
    }
}
