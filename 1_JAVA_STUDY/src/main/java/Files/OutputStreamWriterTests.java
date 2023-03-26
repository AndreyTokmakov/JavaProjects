package Files;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class OutputStreamWriterTests
{
    private static final String fileName = "S:\\Temp\\TESTING_ROOT_DIR\\TestFile.log";

    // Simple example how to use OutputStream to write to file
    protected static void WriteToFile()
    {
        String data = "qwerty";
        byte[] bytes = data.getBytes();
        try (final OutputStream out = new FileOutputStream(fileName)) {
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("Done");
        }
    }

    public static void main(String[] args) throws IOException {
        // Writer writer = new OutputStreamWriter(System.out, StandardCharsets.US_ASCII);
        // writer.write("sdsdsdd");

        WriteToFile();
    }
}
