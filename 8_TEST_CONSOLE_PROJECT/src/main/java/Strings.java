import java.util.ArrayList;
import java.util.List;

public class Strings {

	protected void SubStrings_LexicographicalOrder() {
		final String text = "welcometojava";
		
		ArrayList<String> strings = new ArrayList<String>();
		for (int i = 0; i <= text.length() - 3;i++) {
			strings.add(text.substring(i, i + 3));
		}
		
		strings.sort((s1, s2) -> {return s1.compareTo(s2);} );
		strings.forEach(s -> System.out.println(s));
		System.out.println(strings.get(0) + "\n" + strings.get(strings.size()-1));
		
	}

	protected void SkipSymbols() {
		String text = "Madam, I'm Adam!";

		char[] letters = text.toLowerCase().toCharArray();
		System.out.println(letters);

		short b1 = '0', b2 = '9', b3 = 'a', b4 = 'z', b5 = 'A', b6 = 'Z';
		int size = 0;
		for (char c: letters) {
			if ((c >= b1 && b2 >= c) || (c >= b3 && b4 >= c) || (c >= b5 && b6 >= c))
				letters[size++] = c;
		}
		for (int i = 0; i < size; ++i) {
			System.out.print(letters[i]);
		}
		System.out.println();
	}

	protected void ContainsAll() {
		List<String> strings = List.of("One", "Two");
		final String text = "Call1(One);\nCall2(One);\n";

		System.out.println(text);
		boolean b = strings.stream().allMatch(text::contains);
		System.out.println(b);
	}
	
	/////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		Strings tests = new Strings();
		// tests.SubStrings_LexicographicalOrder();

		// tests.SkipSymbols();
		// tests.ContainsAll();
	}
}
