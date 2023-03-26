/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java bitwise pperations demo
*
* @name      : Bit_Tests.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : November 01, 2020
****************************************************************************/ 

package BitwiseOperation;

public class Bit_Tests {

	private static final int INT_BYTES = 32;

	public static void showBits(long number) {
		System.out.print(number + " = ");
		for (int i = INT_BYTES - 1; i >= 0; i--) {
			char c = (0 == (number & (1 << i))) ? '0' : '1';
			System.out.print(c);
		}
		System.out.println();
	}

	public static long setBit(long number, long bit) {
		return (number |= (1 << (bit)));
	}

	public static long unsetBit(long number, long bit) {
		return (number &= (~(1 << bit)));
	}

	public static long flipBit(long value, int bitIndex) {
		boolean set = (0 == (value & (1 << bitIndex)));
		if (true == set) {
			value |= (1 << (bitIndex));
		}
		else {
			value &= (~(1 << bitIndex));
		}
		return value;
	}

	public static long flipBits(long number) {
		for (int bit = INT_BYTES - 1; bit >= 0; bit--) {
			boolean set = (0 == (number & (1 << bit)));
			if (true == set) {
				number |= (1 << (bit));
			}
			else {
				number &= (~(1 << bit));
			}
		}
		return number;
	}

	public static long flipBits2(long number) {
		return ~number;
	}

	//--------------------------------------------------------------

	public static void SetBitTest() {
		long num = 20, bit = 4;
		showBits(num);
		num = unsetBit(num, bit);
		showBits(num);
	}

	public static void FlipOneBitTest() {
		long num = 20;
		showBits(num);
		num = flipBit(num, 2);
		showBits(num);
		num = flipBit(num, 2);
		showBits(num);
	}

	public static void FlipBitTest() {
		long num = 20;
		showBits(num);
		num = flipBits(num);
		showBits(num);
	}

	public static void FlipBitTest2() {
		long num = 20;
		showBits(num);
		num = flipBits2(num);
		showBits(num);
	}
	
	public static void main(String[] args)
	{
		// SetBitTest();

		FlipOneBitTest();

		// FlipBitTest();
		// FlipBitTest2();
	}
}
