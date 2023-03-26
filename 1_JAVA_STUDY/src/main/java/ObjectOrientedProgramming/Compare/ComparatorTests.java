package ObjectOrientedProgramming.Compare;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/** Person class : **/
class Person {
	private int pid;
	private String name;
	private LocalDateTime birth_date = null;
	private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	public Person() {
		// TODO
	}
	public Person(int pid, 
				  final String name){
		this.pid = pid;
		this.name = name;
		this.birth_date = LocalDateTime.now();
	}
	public Person(int pid, 
				  final String name,
				  final LocalDateTime birth_date){
		this.pid = pid;
		this.name = name;
		this.birth_date = birth_date;
	}
	public Person(int pid, 
				  final String name,
			      final String birth_date_str){
		this.pid = pid;
		this.name = name;
		this.birth_date = LocalDateTime.parse(birth_date_str, Person.formatter);
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime geBirthDate() {
		return this.birth_date;
	}	
} 

/** StringComparer class : **/
class StringComparer implements Comparator<String> {
	@Override
	public int compare(String str1, String str2) {
		return str1.compareTo(str2);
	}

	/*
	@Override
	public boolean equals(Object obj) {
	
	}
	*/
} 

/** PersonComparer class : **/
class PersonComparer implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		return p1.getName().compareTo(p2.getName());
	}
} 

/** PersonComparer class : **/
class PersonComparerDate implements Comparator<Person> {
	@Override
	public int compare(Person p1, Person p2) {
		return p1.geBirthDate().compareTo(p2.geBirthDate());
	}
} 

/** SortList class : **/
public class ComparatorTests {

	/** Sort list using the **/
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
	
	/** Sort by time **/
	public void Sort_ByBirthDate() {
		final Consumer<Person> personPrinter = 
				(Person p) -> System.out.println("id : " + p.getPid() +" , Name : " + p.getName() + ", Date: " + p.geBirthDate());		
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(new Person(1, "Mahesh",  "2019-08-28 10:00:10"));
		persons.add(new Person(2, "Ram",     "2019-08-28 10:20:00"));
		persons.add(new Person(3, "Krishna", "2019-08-28 10:15:00"));
		persons.add(new Person(4, "Anna",    "2019-08-28 10:00:55"));
		persons.add(new Person(5, "John",    "2019-08-28 11:00:00"));
		persons.add(new Person(6, "Ben",     "2019-08-28 09:00:00"));
		
		System.out.println("Before :");
		persons.forEach(personPrinter);
		
		persons.sort(new PersonComparerDate());
		
		System.out.println("After :");
		persons.forEach(personPrinter);

	}	
	
	
	///////////////////////////// MAIN //////////////////////////////
	public static void main(String[] args) {
		ComparatorTests tests = new ComparatorTests();
		// tests.ListP_Sort_Comparer();
		// tests.SortStringList();
		tests.Sort_ByBirthDate();
	}
}
