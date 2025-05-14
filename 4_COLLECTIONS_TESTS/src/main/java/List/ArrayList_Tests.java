package List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.StringJoiner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Utilities.Build;
import Utilities.Person;
import Utilities.TestClass;

import java.lang.reflect.Field;


public class ArrayList_Tests
{
	class PersonComparer implements Comparator<Person> {
		@Override
		public int compare(Person p1, Person p2) {
			return p1.getName().compareTo(p2.getName());
		}
	}

	/** StringComparer class : **/
	class StringComparer implements Comparator<String> {
		@Override
		public int compare(String str1, String str2) {
			return str1.compareTo(str2);
		}
	}



	protected final Consumer<String> simplePrinter = (String value) -> System.out.println("  " + value);	
	
	public <U extends List<T>, T> 
	int getCapacity(final U list) throws Exception {
        final Field dataField = ArrayList.class.getDeclaredField("elementData");
        dataField.setAccessible(true);
        return ((Object[]) dataField.get(list)).length;
    }
	
	/*
    int getCapacity(ArrayList<?> l) throws Exception {
        Field dataField = ArrayList.class.getDeclaredField("elementData");
        dataField.setAccessible(true);
        return ((Object[]) dataField.get(l)).length;
    }
    */
	
	public void CreateList() {
		{
			List<String> list1 = Arrays.asList("milan", "riga", "istambul", "moscow");
			System.out.println(list1);
		}
		{
			List<String> cities = List.of("milan", "riga", "istambul", "moscow");
			System.out.println(cities);
		}
	}
	
	public void ContainsAll() {
		final List<String> list1 = Arrays.asList("milan", "riga", "istambul", "moscow");
		final List<String> list2 = Arrays.asList("riga", "milan", "moscow", "istambul");
		
		boolean b1 = list1.containsAll(list2);
		boolean b2 = list2.containsAll(list2);
		
		System.out.println(b1);
		System.out.println(b2);
	}
	
	public void DeleteFromList() {
		ArrayList<Build> builds = new ArrayList<Build>();
		builds.add(new Build(1, "uuid1"));
		builds.add(new Build(2, "uuid2"));
		builds.add(new Build(3, "uuid3"));
		
		builds.forEach(b -> System.out.println(b));
		
		Build buildToFind = new Build(2, "uuid2");
		
		int index = builds.indexOf(buildToFind);
		System.out.println(String.format("\nIndex of Build %s is %d.", buildToFind, index));
		
		builds.remove(buildToFind);
		
		System.out.println(String.format("\nDeleting of build %s.", buildToFind));
		builds.forEach(b -> System.out.println(b));
	}
		
	
	public void ListPrinter() {
		final Consumer<String> printer = (String value) -> System.out.println(value);	
		List<String> testList = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5", "Value6", "Value7"));
		testList.forEach(printer);
	}
	
	public void ListPrinter2() {
		List<String> testList = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5", "Value6", "Value7"));
		testList.forEach(System.out::println);
	}
	
	
	public void ListInitialyzeTest() {
		final Consumer<String> printer = (String value) -> System.out.println(value);	
		
		System.out.println("------List1:");
		List<String> values = Collections.unmodifiableList(Arrays.asList("Value1", "Value2", "Value3"));
		values.forEach(printer);
		
		System.out.println("\n------List2:");
		
		List<String> testList = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5", "Value6", "Value7"));
		testList.forEach(printer);
	}
	
	public void ListInitialyzeTest2() {
		ArrayList<String> values = new ArrayList<String>(10);
		values.addAll(Arrays.asList("Value1", "Value2", "Value3"));
		

		System.out.println(values);
	}
	
	public void List_Comma_Between_Values_Test_1() {
		List<String> values = Collections.unmodifiableList(Arrays.asList("Value1", "Value2", "Value3"));
		final String result = values.stream().map(Object::toString).collect(Collectors.joining(",")).toString();
		System.out.println(result);
	}
	
	public void List_Comma_Between_Values_Test_2() {
		List<String> values = Collections.unmodifiableList(Arrays.asList("Value1", "Value2", "Value3"));
		StringJoiner joiner = new StringJoiner(",");
		int i = 0;
		for (final String item : values) {
		    //joiner.add(item.toString());
			i++;
			joiner.add("{\"year\": " + i + ",\"Passed\": " + i + ",\"Failed\": " + i + ",\"Skipped\": " + i  + "}");
		}
		System.out.println(joiner.toString());
	}
	
	public void List_Comma_Between_Values_Test_3() {
		List<String> values = Collections.unmodifiableList(Arrays.asList("Value1", "Value2", "Value3"));
		String delim = "";
		StringBuilder sb = new StringBuilder();
		for (final String item : values)  {
		    sb.append(delim).append(item);
		    delim = ",";
		}
		System.out.println(sb.toString());
	}
	
	
	public void Check_IF_Contains()
	{
		final Consumer<TestClass> printer = (final TestClass obj) -> System.out.println(obj.name + " (" + obj.value + ")");
		List<TestClass> testList = new ArrayList<TestClass>();
		for (int i = 0; i < 10; i++)
			testList.add(new TestClass("Value" + i, i));
		
		long val = 5;
		boolean exists = testList.stream().anyMatch(element -> val == element.value);
		
		System.out.println(exists);
		//testList.forEach(printer);
	}
	
	public void Update_Element()
	{
		{
			List<TestClass> values = 
					Collections.synchronizedList(Arrays.asList(new TestClass("Value1", 1), 
															   new TestClass("Value2", 2), 
															   new TestClass("Value3", 3)));
			values.forEach(x -> System.out.println(x));
			
			int index = values.indexOf(new TestClass("Value2", 2));
			values.set(index, new TestClass("Value123", 123));
			
			System.out.println("---------------------- After update:");
			
		    values.forEach(x -> System.out.println(x));
		}
		
		System.out.println("---------------------- Test2: ---------------------");
	    
		{
			List<Integer> values = Arrays.asList(1,2,3,4,5);
			System.out.println(values);
			//System.out.println(values.get(1).);
		}
	}
	
	public void Update_Element_2()
	{
		List<TestClass> values = new ArrayList<TestClass>(Arrays.asList(new TestClass("Value1", 1), 
																		new TestClass("Value2", 2),
																		new TestClass("Value3", 3)));
		values.add(new TestClass("Value4", 4));
		TestClass ptr = values.get(values.size() -1);
		
		values.forEach(x -> System.out.println(x));
		
		System.out.println();
		ptr.setName("Value555");
		
	    values.forEach(x -> System.out.println(x));
	    
	    values.add(new TestClass("Value6", 6));
	    
	    System.out.println();
	    ptr.setValue(555);
		
	    values.forEach(x -> System.out.println(x));
	    
	}
	
	public void Skip_Element() {
		final String[] elements = {"One", "Two", "Three", "Four", "Five"};
		Arrays.asList(elements).stream().skip(1).forEach(v -> {System.out.println(v);});
	}
	
	public void Get_Last_Element() {
		List<String> list = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5", "Value6", "Value7"));
		
		final String entry = list.get(list.size() - 1);
		
		System.out.println("last element: " + entry);
		System.out.println("First element: " + list.stream().findFirst().get());
		
		final int n = 7;
		System.out.println("Last " + n + " elements: " + list.subList(list.size() - n, list.size())); 
		
		 
	}
	
	public void Filter_List() {
		List<String> list = 
				new ArrayList<String>(Arrays.asList("ValueAA", "Value2", "ValueAA", "Value4", "ValueAA", "Value6", "Value7"));
		
		List<String> list2 = list.stream().filter(e -> true == e.contains("AA")).collect(Collectors.toList());
		list2.forEach(simplePrinter);
	}
	
	public void Sort_list() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(5,1,2,6,9,4,3,7,8));
		
		System.out.println(list);
		
		Collections.sort(list);
		System.out.println(list);
		
		Collections.sort(list, (var1, var2) -> {return var2.compareTo(var1);});
		System.out.println(list);
	}
	
	public void ListP_Sort_Comparer() {
		final Consumer<Person> personPrinter = (Person p) -> System.out.println("id : " + p.getPid() +" , Name : " + p.getName());		
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(new Person(1, "Mahesh"));
		persons.add(new Person(2, "Ram"));
		persons.add(new Person(3, "Krishna"));
		persons.add(new Person(4, "Anna"));
		persons.add(new Person(5, "John"));
		persons.add(new Person(6, "Ben"));
		
		System.out.println("Before :");
		persons.forEach(personPrinter);
		
		persons.sort(new PersonComparer());
		
		System.out.println("After :");
		persons.forEach(personPrinter);
	}	
	
	/** Sort list using the **/
	public void SortStringList() {
		final Consumer<String> printer = (String value) -> System.out.println(value);		
		List<String> strigns = new ArrayList<String>();
		
		strigns.add("Mahesh");
		strigns.add("Ram");
		strigns.add("Krishna");
		strigns.add("Anna");
		strigns.add("John");
		
		
		System.out.println("Before :");
		strigns.forEach(printer);
		
		strigns.sort(new StringComparer());
		
		System.out.println("\n\nAfter :");
		strigns.forEach(printer);
	}		
	

	/** Sort list using the **/
	public void SortStringList_Lambda() {
		final Consumer<String> printer = (String value) -> System.out.println(value);		
		List<String> strigns = new ArrayList<String>();
		
		strigns.add("Mahesh");
		strigns.add("Ram");
		strigns.add("Krishna");
		strigns.add("Anna");
		strigns.add("John");
		
		
		System.out.println("Before :");
		strigns.forEach(printer);
		
		strigns.sort((str1, str2)-> str1.compareTo(str2));
		
		System.out.println("\n\nAfter :");
		strigns.forEach(printer);
	}	
	
	
	public void KeepSameElements_RetainAll()
	{
		List<String> list1 = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5", "Value6", "Value7"));
		List<String> list2 = new ArrayList<String>(Arrays.asList("Value3", "Value4", "Value5", "Value6", "Value7", "Value8", "Value9"));
		List<String> listSame = list1;
		
		listSame.retainAll(list2);
		
		for (String value : list1)
			System.out.println(value);		
	}
	
	public void GetSubList() {
		ArrayList<String> originalList = new ArrayList<String>();

		//Addition of elements in ArrayList
		for (int i = 1; i <= 10; i++)
			originalList.add("Value_" + i);

		System.out.println("Original ArrayList Content: " + originalList);

		//Sublist to ArrayList
		ArrayList<String> subList = new ArrayList<String>(originalList.subList(0, 4));
		System.out.println("SubList stored in ArrayList: " + subList);
		
		for (String v : originalList)
			System.out.println(v);
	}
	
	
	public void Print_Reverse() {
		List<String> list = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5", "Value6", "Value7"));
		ListIterator<String> listIter = list.listIterator(list.size());
		while (listIter.hasPrevious()) {
			System.out.println(listIter.previous());
		}
	}
	
	public void Init_Random() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++)
			list.add(i);
		
		Random random = new Random();
		while (false == list.isEmpty()) {
			int pos = random.nextInt(list.size());
			System.out.println(list.get(pos));
			list.remove(pos);
		}	
	}
	
	public void Remove() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(12, 1, 78, 12, 23));
		list.remove(0);
		System.out.println(list);
	}
	
	public void Remove_Last() {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		
		while (false == list.isEmpty()) {
			System.out.println(list);
			list.remove(list.size() - 1);
		}
	}
	
	public void RemoveIf() {
		List<String> testList = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5", "Value6", "Value7"));
		Predicate<String> filter = (String value) -> (value.contains("3") || value.contains("5") );
		
		System.out.println("Before:");
		testList.forEach(simplePrinter);
		
		testList.removeIf(filter);
		
		System.out.println("After update:");
		testList.forEach(simplePrinter);
	}	
	
	public void RemoveAll() {
		List<String> list1 = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3", "Value4", "Value5", "Value6", "Value7"));
		List<String> list2 = new ArrayList<String>(Arrays.asList("Value1", "Value2", "Value3"));
		
		System.out.println("Before:" + list1);
		System.out.println("Removing list :" + list2);
		
		list1.removeAll(list2);
		System.out.println("\nResult:" + list1);
	}
	
	public void TrimToSize() throws Exception
	{
		// creating an Empty Integer ArrayList 
		ArrayList<Integer> numbers = new ArrayList<Integer>(9); 

        // using add(), add 5 values 
		numbers.add(2); 
        numbers.add(4); 
        numbers.add(5); 
        numbers.add(6); 
        numbers.add(11);    

        System.out.println("Capacity = " + this.getCapacity(numbers));
        
        numbers.trimToSize(); 
  
        System.out.println("\nAfter:");
        System.out.println("Numbers are : " + numbers);
        System.out.println("Capacity = " + this.getCapacity(numbers));
	}


	public void swapElements()
	{
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(
				"Value1", "Value2", "Value3", "Value4", "Value5"
		));
		System.out.println(list);

		Collections.swap(list, 0, 4);
		System.out.println(list);
	}

	public void swapElements_CustomClass()
	{
		ArrayList<TestClass> list = new ArrayList<TestClass>(Arrays.asList(
				new TestClass("One", 1),
				new TestClass("Two", 2),
				new TestClass("Three", 3),
				new TestClass("Four", 4),
				new TestClass("Five", 5)
		));
		System.out.println(list);

		Collections.swap(list, 0, 4);
		System.out.println(list);
	}

	public void moveElementsToFront()
	{
		final ArrayList<TestClass> list = new ArrayList<TestClass>(Arrays.asList(
				new TestClass("One", 1),
				new TestClass("Two", 2),
				new TestClass("Three", 3),
				new TestClass("Four", 4),
				new TestClass("Five", 5)
		));
		System.out.println(list);

		int indexToMove = 4;
		final TestClass element = list.get(indexToMove);
		list.remove(indexToMove);
		list.add(0,element);

		System.out.println(list);
	}

	public void Merge_Two_Lists_Into_One()
	{
		List<String> list1 = List.of("one", "two");
		List<String> list2 = List.of("three", "two");

		List<String> combined =  Stream.of(list1, list2).flatMap(List::stream).toList();
		System.out.println(combined);
	}

	public static void main(String[] args) throws Exception
	{
		ArrayList_Tests tests = new ArrayList_Tests();

		tests.Merge_Two_Lists_Into_One();
		
		// tests.CreateList();
		
		// tests.Remove();
		// tests.Remove_Last();
		// tests.RemoveIf();
		// tests.RemoveAll();

		// tests.swapElements();
		// tests.swapElements_CustomClass();
		// tests.moveElementsToFront();

		
		// tests.ContainsAll();
		// tests.DeleteFromList();
		
	    // tests.ListInitialyzeTest();
		// tests.ListInitialyzeTest2();
		
		// tests.ListPrinter();
		// tests.ListPrinter2();
		
		// tests.TrimToSize();
		
		// tests.Sort_list();

		// tests.List_Comma_Between_Values_Test_1();
		// tests.List_Comma_Between_Values_Test_2();
		// tests.List_Comma_Between_Values_Test_3();
		
		// tests.Check_IF_Contains();
		
		// tests.Update_Element();
		// tests.Update_Element_2();
		
		 //tests.Skip_Element();
		
		// tests.Get_Last_Element();
		
		// tests.ListP_Sort_Comparer();
		// tests.SortStringList();
		// tests.SortStringList_Lambda();
		
		// tests.KeepSameElements_RetainAll();
		// tests.GetSubList();
		
		// tests.Print_Reverse();
		
		// tests.Init_Random();
	}	
}

