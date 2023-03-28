package wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

// https://wireless.wiki.kernel.org/en/users/documentation/iw

// TODO: Need to get WiFi Interfaces
public class WIFIManager
{
    public List<String> exec(final List<String> cmd) {
        final ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        try {
            final Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (0 != process.waitFor())  // TODO: Use timeout??
                throw new RuntimeException("Wait for process failed");

            // int code = process.exitValue()); // TODO: Check code
            List<String> lines = reader.lines().toList();
            process.destroy();
            return lines;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: Need to refactored
    // TODO: Parse response:
    public void GetAccessPoints()
    {
        // List<String> lines = this.exec(List.of("nmcli", "d", "wifi", "list", "--rescan", "yes"));
        List<String> result = this.exec(List.of("nmcli", "d", "wifi", "list"));
        //result.forEach(System.out::println);

        for (String line: result)
        {
            StringTokenizer tokens = new StringTokenizer(line);
            while (tokens.hasMoreTokens())
                System.out.print(tokens.nextToken() + " | ");
            System.out.println();
        }
    }

    // FIXME: Rename?? What to return ??
    // TODO : Refactor parse: 'iw IFACE link' or 'iwconfig IFACE'
    //        Parse return Data --> Structure / Class
    public void GetConnectionStatus(final String interfaceName)
    {
        final List<String> result = this.exec(List.of("iw", interfaceName, "link"));
        final Optional<String> ssid = result.stream().filter(entry -> entry.contains("SSID")).map(String::trim).findFirst();
        if (ssid.isPresent()) {
            String apName = ssid.get().replace("SSID: ", "");
            System.out.println(apName);
        }
        else {
            System.err.println("Not connected");
        }
    }

    // TODO: Refactor ????
    public Boolean CheckIsConnectedToAccessPoint(String interfaceName, String apName)
    {
        final List<String> result = this.exec(List.of("iw", interfaceName, "link"));
        return result.stream().filter(e -> {
            return e.contains("SSID") && e.contains(apName);
        }).map(String::trim).findFirst().isPresent();
    }

    // TODO: Refactor
    public Boolean ConnectToWiFiAccessPoint(String apName, String password)
    {
        final List<String> result = this.exec(List.of("nmcli", "d", "wifi", "connect", apName, "password", password));
        return result.stream().anyMatch(s -> s.contains("successfully activated"));

    }

    public static void main(String[] args)
    {
        WIFIManager mgr = new WIFIManager();
        // mgr.GetAccessPoints();
        // mgr.GetConnectionStatus("wlp0s20f3");

        // System.out.println(mgr.CheckIsConnectedToAccessPoint("wlp0s20f3", "Unikie"));

        Boolean result = mgr.ConnectToWiFiAccessPoint("comms_sleeve#6027", "ssrcdemo");
        System.out.println(result);
    }
}

