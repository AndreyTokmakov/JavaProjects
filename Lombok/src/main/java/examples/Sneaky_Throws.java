package examples;

import lombok.SneakyThrows;

import java.io.UnsupportedEncodingException;

public class Sneaky_Throws
{
    public static class SneakyThrowsExample implements Runnable
    {
        @SneakyThrows(UnsupportedEncodingException.class)
        public String utf8ToString(byte[] bytes) {
            return new String(bytes, "UTF-8");
        }

        @SneakyThrows
        public void run() {
            throw new Throwable();
        }
    }

    public static void main(String[] args) {

    }
}
