package wifi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class WIFIManager {
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

    public static void main(String[] args)
    {
        WIFIManager mgr = new WIFIManager();
        mgr.GetAccessPoints();


        // List<String> lines = mgr.exec(List.of("nmcli", "connection", "show"));
        // lines.forEach(System.out::println);
    }
}

