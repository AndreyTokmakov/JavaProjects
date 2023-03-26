package Stream;

import java.util.stream.IntStream;

public class IntStreamTest {
	
	 // this need static
    static IntStream rangeProvider() {
        return IntStream.range(0, 10);
    }

    public static void main(String[] args) {
        IntStream intStream = IntStream.range(20, 30);
        intStream.forEach(System.out::println);
     }
}
