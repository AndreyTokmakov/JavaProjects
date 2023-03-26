package Strings;


public class StringBuilderTests {

	protected void CreateTest() {
		StringBuilder str1= new StringBuilder();
		System.out.println("Content: " + str1.toString() + ", Length: " + str1.capacity() + ", Length: ");
		
		StringBuilder str2 = new StringBuilder(10);
		System.out.println("Content: " + str2.toString() + ", Length: " + str2.capacity());
		
		StringBuilder str3 = new StringBuilder("0123456789");
		System.out.println("Content: " + str3.toString() + ", Length: " + str3.capacity());
	}
	
	protected void SubString() {
	    String numbers = "0123456789";

	    StringBuilder sb = new StringBuilder(numbers);

	    System.out.println(sb.substring(3));    //3456789
	    System.out.println(sb.substring(4, 8)); //4567
	}
	
	protected void ReverserString() {
	    String numbers = "0123456789";
	    StringBuilder stringBuilder = new StringBuilder(numbers);
	    System.out.println(stringBuilder.reverse());
	}
	
	protected void Append() {
	    StringBuilder stringBuilder = new StringBuilder("TEST");
	    stringBuilder.append("_11");
	    System.out.println(stringBuilder);
	}
	
	protected void Delete() {
	    final String numbers = "0123456789";
	    StringBuilder sb = new StringBuilder(numbers);
		System.out.println(sb.delete(5, 9)); //012349
	    System.out.println(sb.deleteCharAt(1)); //02349
	}
	
	protected void DeleteCharAt() {
	    StringBuilder strBuilder  = new StringBuilder("0123456789");
		System.out.println(strBuilder); 
		strBuilder.deleteCharAt(3);
	    System.out.println(strBuilder); 
	}
	
	private void __printer(final String text) {
		System.out.println(text);
	}
	
	protected void PassAsParam() {
		StringBuilder stringBuilder = new StringBuilder("TEST_123");
		__printer(stringBuilder.toString());
	}
	
	protected void Insert() {
		StringBuffer strBuffer = new StringBuffer("0123456789");
		System.out.println(strBuffer);
		
		strBuffer.insert(3, "_3_");
		System.out.println(strBuffer.toString());
		 
		strBuffer.insert(0, "New___");
		System.out.println(strBuffer.toString());
	}
	
	protected void SetLength() {
		StringBuffer strBuffer = new StringBuffer("Hello World");
		System.out.println(strBuffer);
		strBuffer.setLength(5);
		System.out.println(strBuffer);
	}
	
	protected void Replace() {
		StringBuffer strBuffer = new StringBuffer("Hello World");
		System.out.println(strBuffer);
		strBuffer.replace(0, 5, "HELLO");
		System.out.println(strBuffer);
	}
	
	protected void IndexOf() {
		StringBuilder stringBuilder = new StringBuilder("some_test_string_test");
		
		int index = stringBuilder.indexOf("test", 0);
		System.out.println(index);
		
		index = stringBuilder.lastIndexOf("test", 0);
		System.out.println(index);
	}
	
	protected void TEST() {
		StringBuilder stringBuilder = new StringBuilder("TEST");
		
		stringBuilder.append("_Added_");
		stringBuilder.append(123);
		
		System.out.println(stringBuilder);
	}
	
	
	public static void main(String[] args) {
		StringBuilderTests tests = new StringBuilderTests();
		
		// tests.CreateTest();
		// tests.SubString();
		// tests.ReverserString();
		// tests.Append();
		 tests.Delete();
		// tests.DeleteCharAt();
		// tests.PassAsParam();
		// tests.Insert();
		// tests.SetLength();
		// tests.Replace();
		
		// tests.IndexOf();
		
		// tests.TEST();
	}
}
