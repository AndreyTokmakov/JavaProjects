import org.testng.annotations.Test;

// TODO: Add logging
// TODO: Add BaseClass for tests ??
// TODO: XML configuration for tests

public class BasicTests
{
    private static final String CSL_AP_WIFI = "comms_sleeve#5bfc";

    @Test(description="WiFi DeAuthentication test")
    public void DeAuthenticationTest()
    {
        System.out.println("1. Check the Comms_Sleeve WiFi access point is available");

        System.out.println("2. Connect to CSL AP:");
        System.out.println("   - Check connection from Client machine");
        System.out.println("   - Check connection on the CSL side");

        System.out.println("3. Run WiFi DeAuthentication attack");

        System.out.println("4. Check that WiFi connection with CSL AP is lost:");
        System.out.println("   - Check NO connection from Client machine");
        System.out.println("   - Check NO connection on the CSL side");
    }

}
