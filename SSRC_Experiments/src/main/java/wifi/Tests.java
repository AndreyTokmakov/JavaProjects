package wifi;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Tests
{
    private static final String clientMAC = "18:f0:e4:1f:b2:84";
    private static final String cslWiFiInterface = "wlan1";

    public static void sleep(int milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // TODO: Use Awaitability
    public static void WaitForClientDisconnected(SSHClientRemote sshClient)
    {
        if (!sshClient.isClientConnected(clientMAC, cslWiFiInterface))  {
            System.err.println("Client " + clientMAC + " is not connected. No tests to run.");
            return;
        }

        System.out.println("Client " + clientMAC + " is connected. Starting test");

        final Instant start = Instant.now();
        final long timeoutSeconds = 10;
        while (timeoutSeconds >= Duration.between(start, Instant.now()).getSeconds())
        {
            if (!sshClient.isClientConnected(clientMAC, cslWiFiInterface))  {
                System.err.println("Client " + clientMAC + " has been disconnected. OK");
                return;
            }
            sleep(100);
        }

        System.err.println("FAILURE: Client " + clientMAC + " is still connected. " + timeoutSeconds + " has passed.");
    }
    public static void main(String[] args)
    {
        SSHClientRemote client = new SSHClientRemote(Credentials.CSL_USER, Credentials.CSL_USER, Credentials.CSL_HOST);

        WaitForClientDisconnected(client);

    }
}
