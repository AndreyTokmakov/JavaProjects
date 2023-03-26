package StepicTasks;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

final class MyCharSequence implements CharSequence {
    final char[] buffer;

    public MyCharSequence(char[] buffer) {
        this.buffer = buffer;
    }

    @Override
    public int length() {
        return this.buffer.length;
    }

    @Override
    public char charAt(int index) {
        return this.buffer[index];
    }

    @Override
    public boolean isEmpty() {
        return 0 == this.buffer.length;
    }

    @Override
    public MyCharSequence subSequence(int start, int end) {
        return new MyCharSequence(Arrays.copyOfRange(buffer, start, end));
    }

    @Override
    public String toString() {
        return Arrays.toString(buffer);
    }
}

final class AsciiCharSequence implements CharSequence {
    final byte[] buffer;

    public AsciiCharSequence(byte[] buffer) {
        this.buffer = buffer;
    }

    @Override
    public int length() {
        return this.buffer.length;
    }

    @Override
    public char charAt(int index) {
        return (char)this.buffer[index];
    }

    @Override
    public boolean isEmpty() {
        return 0 == this.buffer.length;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(buffer, start, end));
    }

    @Override
    public String toString() {
        return new String(buffer, StandardCharsets.UTF_8);
    }
}


public class CharSequenceTests
{

    private final static class A {

    }

    private final static class B {

    }

    public static void test1() {
        char[] buffer = "123456789".toCharArray();

        MyCharSequence seq = new MyCharSequence(buffer);

        // System.out.println(seq.charAt(2));
        // System.out.println(seq.length());
        System.out.println(seq);

        MyCharSequence seq2 = seq.subSequence(1, 4);
        System.out.println(seq2);
    }

    public static void main(String[] args)
    {
        AsciiCharSequence seq = new AsciiCharSequence(new byte[] {'A', 'B', 'C'});
        System.out.println(seq.charAt(1));
        System.out.println(seq);

    }
}
