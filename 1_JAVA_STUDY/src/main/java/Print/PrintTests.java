package Print;

public class PrintTests {
	
	public String hack(int i)
    {
        if (100 > i)
            return "0" + i;
        else 
            return "" + i;
    }

	public static void main(String[] args) {
		System.out.println(String.format("%-15s%d" , "label", 111));
	}

}
