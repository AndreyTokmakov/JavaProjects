package wifi;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Tests
{
    private static final String clientMAC = "18:f0:e4:1f:b2:84";
    private static final String clientWiFiInterface = "wlp0s20f3";


    private static final String cslWiFiInterface = "wlan1";
    private static final String cslWiFiAPName = "comms_sleeve#6027";
    private static final String cslWiFiAPPassword = "ssrcdemo";

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

    public static Boolean ConnectToCSLWiFiAccessPoint(SSHClientRemote sshClient)
    {
        // TODO: Logging
        System.out.println("Connecting from " + clientMAC + " to " + cslWiFiAPName);

        if (!sshClient.CheckIsConnectedToAccessPoint(clientWiFiInterface, cslWiFiAPName))
        {
            if (!sshClient.ConnectToWiFiAccessPoint(cslWiFiAPName, cslWiFiAPPassword)) {
                System.err.println("Failed to connect to '" + cslWiFiAPName + "' Access Point");
                return false;
            }
        }

        if (!sshClient.CheckIsConnectedToAccessPoint(clientWiFiInterface, cslWiFiAPName)) {
            System.err.println("Client is still not connected to '" + cslWiFiAPName + "' Access Point");
            return false;
        }

        if (!sshClient.isClientConnected(clientMAC, cslWiFiInterface))  {
            System.err.println("Client " + clientMAC + " is not connected to CSL at '" + cslWiFiInterface + "'");
            return false;
        }

        return true;
    }

    public static void main(String[] args)
    {
        SSHClientRemote client = new SSHClientRemote(Credentials.CSL_USER, Credentials.CSL_USER, Credentials.CSL_HOST);

        ConnectToCSLWiFiAccessPoint(client);
        WaitForClientDisconnected(client);
        // TODO: DeAuth attack
        // TODO: Check Disconnection
    }
}
