package PerfromanceComparison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ArrayList_vs_HashMap {
	
	private final static class Buffer {
		private String name = "None";
		
		public Buffer(String name) {
			this.name = name;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	private final static class TESTS
	{
		protected void RunTest() throws InterruptedException 
		{	
			final int size = 33;
			final String prefix = "TextContent_";
			
			final List<String> list       = new ArrayList<String>(size);
			final Map<String, Buffer> map = new HashMap<String, Buffer>(size);
			final String[] list2 = new String[size];
			
			for (int i = 0; i < size; ++i) {
				String text = prefix + i;
				list.add(text);
				list2[i] = text;
				map.put(text, new Buffer(text));
			} 
			
			
			final long testsCount = 100000000;
			final String toFind = "TextContent_12";
			
			{
				long start = System.currentTimeMillis();
				Buffer result = null;
				for (long i = 0; i < testsCount; ++i) {
					result = map.get(toFind);
					// System.out.println(result.getName());
				}
				long end = System.currentTimeMillis();
				System.out.println("Result : " + (end - start));
			}
			
			
			{
				long start = System.currentTimeMillis();
				String result = "";
				for (long i = 0; i < testsCount; ++i) {
					for (int n = 0; n < size; ++n) {
						if (true == list2[n].equals(toFind))
						{
							result = list2[n];
							break;
						}
					}
				}
				long end = System.currentTimeMillis();
				System.out.println("Result : " + (end - start));
			}
			
			{
				long start = System.currentTimeMillis();
				String result = "";
				for (long i = 0; i < testsCount; ++i) {
					for (String buff: list) {
						if (true == buff.equals(toFind))
						{
							result = buff;
							// System.out.println(result);
							break;
						}
					}
				}
				long end = System.currentTimeMillis();
				System.out.println("Result : " + (end - start));
			}
		}
	}
	
	private final static TESTS tests = new TESTS();
	
	public static void main(String[] args) throws InterruptedException
	{
		tests.RunTest();

	}
}
