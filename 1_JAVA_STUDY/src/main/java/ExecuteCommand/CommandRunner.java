package ExecuteCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandRunner
{
    public static void exec1(String cmd)
    {
        try {
            final Process process= Runtime.getRuntime().exec(cmd);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            // TODO: Check result
            process.waitFor();

            if (0 != process.exitValue()) {
                System.out.println("Error");
            }
            process.destroy();
            System.out.println(output);
        } catch (Exception ignored) {}
    }

    public static void exec2(final List<String> cmd)
    {
        final ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            process.waitFor();

            System.out.println("Exit code = " + process.exitValue());
            System.out.println(output);

            process.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exec3(final List<String> cmd)
    {
        final ProcessBuilder processBuilder = new ProcessBuilder(cmd);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            process.waitFor();
            List<String> lines = reader.lines().toList();

            System.out.println("Exit code = " + process.exitValue());
            lines.forEach(System.out::println);

            process.destroy();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args)
    {
        // exec1("ls -lar");

        // exec2(Arrays.asList("ls", "-lar"));
        exec3(Arrays.asList("ls", "-lar"));
    }
}
