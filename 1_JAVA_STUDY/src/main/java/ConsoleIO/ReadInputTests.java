package ConsoleIO;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ReadInputTests {

    protected static void WriteToInput_AndRead_Bytes() throws IOException {
        final byte[] passCode = "A\r\n".getBytes();
        final ByteArrayInputStream inStream = new ByteArrayInputStream(passCode);
        System.setIn(inStream);

        byte b = 0;
        while (-1 != (b = (byte)System.in.read())) {

            System.out.write(b);
        }
    }

    public static void main(String[] args) throws IOException {
        WriteToInput_AndRead_Bytes();
    }
}
