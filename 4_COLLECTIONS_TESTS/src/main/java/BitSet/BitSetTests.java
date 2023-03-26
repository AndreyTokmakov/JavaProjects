package BitSet;
import java.util.BitSet;

class Tests {

	public void And_Or_Xor() {
      BitSet bits1 = new BitSet(16);
      BitSet bits2 = new BitSet(16);
      
      // set some bits
      for(int i = 0; i < 16; i++) {
         if((i % 2) == 0) 
        	 bits1.set(i);
         if((i % 5) != 0) 
        	 bits2.set(i);
      }
     
      System.out.println("bits1: ");
      System.out.println(bits1);
      System.out.println("\bits2: ");
      System.out.println(bits2);


      bits2.and(bits1);
      System.out.println("\nbits2 AND bits1: ");
      System.out.println(bits2);

      bits2.or(bits1);
      System.out.println("\nbits2 OR bits1: ");
      System.out.println(bits2);

      bits2.xor(bits1);
      System.out.println("\nbits2 XOR bits1: ");
      System.out.println(bits2);
	}
	
	public void And() {
		BitSet bits1 = new BitSet();
		BitSet bits2 = new BitSet();

		bits2.set(1000001);
		bits1.set(1111111);

		bits2.and(bits1);

		System.out.println(bits2);
	}
	
	public void Create() {
		BitSet bitSet = new BitSet(32);

		bitSet.set(0);
		bitSet.set(1);
		bitSet.set(2);
		
		System.out.println(bitSet);
		System.out.println();
	}
}

public class BitSetTests {
	public static void main(String[] args) {
		Tests tests = new Tests();
		
		tests.Create();
		// tests.And_Or_Xor();
		// tests.And();
	}
}
