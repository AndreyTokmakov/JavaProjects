package StepicTasks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ByteStreamsTests
{
    public InputStream getStream(byte [] data)  {
        return new ByteArrayInputStream(data);
    }

    public int checkSumOfStream1(InputStream inputStream) throws IOException {
        int b = 0, sum = 0;
        while (-1 != (b = inputStream.read())){
            sum = Integer.rotateLeft(sum, 1) ^ b;
        }
        return sum;
    }

    public int checkSumOfStream2(InputStream inputStream) throws IOException {
        byte []bytes = new byte[1024 * 10];
        int size = inputStream.read(bytes), sum = 0;
        for (int i = 0; i < size;++i) {
            sum = Integer.rotateLeft(sum, 1) ^ (int)(bytes[i] & 0xFF);
        }
        return sum;
    }

    public void Test() throws IOException {
        InputStream stream = getStream( new byte[] { 0x33, 0x45, 0x01});

        int result = checkSumOfStream1(stream);
        System.out.print("Result = " + result);
    }

    //------------------------------------------------------------------------

    protected void ReadBytesStream() throws IOException {
        // StandardCharsets.UTF_8
        final byte[] array = new byte[]{48, 49, 50, 51};
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(array);

        final InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        int b = 0;
        final StringBuilder sb = new StringBuilder();
        while (-1 != (b = reader.read()))
            sb.append((char)b);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException
    {
        ByteStreamsTests tests  = new ByteStreamsTests();
        // tests.Test();
        tests.ReadBytesStream();
    }
}
