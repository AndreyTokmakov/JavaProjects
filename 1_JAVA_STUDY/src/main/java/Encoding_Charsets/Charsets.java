package Encoding_Charsets;

import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Charsets {

    public static void UTF8_To_Codes() {
        Charset charset = StandardCharsets.UTF_8;
        byte [] b = "Ы".getBytes(charset);
        int [] result = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            result [i] = Byte.toUnsignedInt(b [i]);
        }
        System.out.print(Arrays.toString(result));
    }


    public static void main(String[] args) {
        // UTF8_To_Codes();

    }
}
