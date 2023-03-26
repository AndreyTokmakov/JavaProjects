/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* WorkingWithBytes.java class
*
* @name    : WorkingWithBytes.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Dec 7, 2020
****************************************************************************/

package Bytes;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;

class Tester {
	
	public void Bytes_PrintAsInteger() 
	{
	    byte data = 0b101;
	    System.out.println(data);
	}
	
	public void Integer_PrintAs_Bytest() 
	{
	    byte octet = 5;
		String bin = String.format("%8s", Integer.toBinaryString(octet)).replace(' ', '0');
		System.out.println(bin);
	}
	
	public void Bytest_Array_Fil()
	{
		byte b[] = new byte[4];

		b[0] = 20;
		b[1] = 10;
		b[2] = 30;
		b[3] = 5;

		for(int i=0;i<b.length;i++)
			System.out.println("Element at Index : "+ i + " " + b[i]);
		
		
		byte b1[] = {20,10,30,5};
	    for(int i =0; i < b.length; i++)
	    	System.out.println("Element at Index : "+ i + " " + b1[i]);
	}
	
	public void IntToByteArray() {
		byte[] bytes = ByteBuffer.allocate(4).putInt(1695609641).array();
		  
	    for (byte b : bytes)
	    	System.out.format("0x%x ", b);
	}
	
	public void ByteArrayToLong() {
		byte [] bytes = { 0, 6, 36, -84, 113, 125, -118, -47 };
	    System.out.println(ByteBuffer.wrap(bytes).getLong());
	    System.out.println(convertByteArrayToLong(bytes));
	}
	
	public  long convertByteArrayToLong(byte[] longBytes) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES);
	    byteBuffer.put(longBytes);
	    byteBuffer.flip();
	    return byteBuffer.getLong();
	}

	//-----------------------------------------------------------------------------------//
	
	public void String_To_BytesArray_OLD() 
	{
		String str = "SomeString";
	    byte[] bytearray = str.getBytes();
	    System.out.println(Arrays.toString(bytearray));
	}
	
	public void String_To_BytesArray() 
	{
		String str = "SomeString";
	    byte[] bytearray = Base64.getDecoder().decode(str);
	    System.out.println(Arrays.toString(bytearray));
	}

	public void Bytes_To_String() 
	{
		String name = "howtodoinjava.com";
        
		byte[] byteArray = name.getBytes();
		
		System.out.println("'howtodoinjava.com' as BytesArray: "+ byteArray );
		 
		String str = new String(byteArray);
		String strWithCharset = new String(byteArray, Charset.defaultCharset());
		 
		System.out.println("Original String: "+ name );
		System.out.println("Obtained String: "+ str );
		System.out.println("Obtained String: "+ strWithCharset );
	}
	
	public void Bytes_To_String_2() 
	{
		final byte[] bytes = "SomeString".getBytes();
		System.out.println(Arrays.toString(bytes));
	    final String string = new String(bytes);
	    System.out.println(string);
	}
}

public class WorkingWithBytes {
	/** **/
	private final static Tester tester = new Tester();
	
	public static void main(String[] args) {
		// tester.Bytes_PrintAsInteger();
		// tester.Integer_PrintAs_Bytest();
		
		// tester.IntToByteArray();
		tester.ByteArrayToLong();
		
		// tester.Bytest_Array_Fil();
		
		// tester.String_To_BytesArray();
		// tester.String_To_BytesArray();
		
		// tester.Bytes2String();
		// tester.Bytes_To_String_2();
	}
}
