package Set;

import java.util.LinkedHashSet;

public class LinkedHashSet_Tests {

	public static void main(String[] args) {
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>();

		for (int i = 10; i > 0; --i)
			linkedHashSet.add(String.valueOf(i));

		linkedHashSet.stream().forEach(System.out::println);

		// TODO: joint values
		String joined = String.join(",", linkedHashSet);
		System.out.println(joined);
	}
}
