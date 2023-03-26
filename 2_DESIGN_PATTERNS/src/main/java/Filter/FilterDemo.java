package Filter;

import java.util.ArrayList;
import java.util.List;

interface Criteria {
	public List<Person> meetCriteria(List<Person> persons);
}

class Person {
	private String name;
	private String gender;
	private String maritalStatus;

	public Person(String name,
	              String gender,
				  String maritalStatus){
		this.name = name;
		this.gender = gender;
		this.maritalStatus = maritalStatus;		
	}

	public String getName() {
		return name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}	
}

class CriteriaMale implements Criteria {
	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> malePersons = new ArrayList<Person>(); 
		for (Person person : persons) {
			if (person.getGender().equalsIgnoreCase("MALE"))
				malePersons.add(person);
		}
		return malePersons;
	}
}

class CriteriaFemale implements Criteria {
	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> femalePersons = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getGender().equalsIgnoreCase("FEMALE"))
				femalePersons.add(person);
		}
		return femalePersons;
	}
}


class CriteriaSingle implements Criteria {
	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> singlePersons = new ArrayList<Person>();
		for (Person person : persons) {
			if (person.getMaritalStatus().equalsIgnoreCase("SINGLE"))
				singlePersons.add(person);
		}
		return singlePersons;
	}
}


class AndCriteria implements Criteria {
	private Criteria criteria;
	private Criteria otherCriteria;

	public AndCriteria(Criteria criteria, Criteria otherCriteria) {
		this.criteria = criteria;
		this.otherCriteria = otherCriteria; 
	}

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);		
		return otherCriteria.meetCriteria(firstCriteriaPersons);
	}
}


class OrCriteria implements Criteria {
	private Criteria criteria1;
	private Criteria criteria2;

	public OrCriteria(Criteria criteria1, Criteria criteria2) {
		this.criteria1 = criteria1;
		this.criteria2 = criteria2; 
	}

	@Override
	public List<Person> meetCriteria(List<Person> persons) {
		List<Person> items1 = criteria1.meetCriteria(persons);
		List<Person> items2 = criteria2.meetCriteria(persons);

		for (Person person : items2) {
			if(!items1.contains(person)){
				items1.add(person);
			}
		}	
		return items1;
	}
}
 
public class FilterDemo {
	public static void main(String[] args) {
		List<Person> persons = new ArrayList<Person>();

		persons.add(new Person("Robert","Male", "Single"));
		persons.add(new Person("John", "Male", "Married"));
		persons.add(new Person("Laura", "Female", "Married"));
		persons.add(new Person("Diana", "Female", "Single"));
		persons.add(new Person("Mike", "Male", "Single"));
		persons.add(new Person("Bobby", "Male", "Single"));

		Criteria male = new CriteriaMale();
		Criteria female = new CriteriaFemale();
		Criteria single = new CriteriaSingle();
		Criteria singleMale = new AndCriteria(single, male);
		Criteria singleOrFemale = new OrCriteria(single, female);

		System.out.println("Males: ");
		printPersons(male.meetCriteria(persons));

		System.out.println("\nFemales: ");
		printPersons(female.meetCriteria(persons));

		System.out.println("\nSingle Males: ");
		printPersons(singleMale.meetCriteria(persons));

		System.out.println("\nSingle Or Females: ");
		printPersons(singleOrFemale.meetCriteria(persons));
   }

	public static void printPersons(List<Person> persons){
		for (Person person : persons) {
			System.out.println("Person : [ Name : " + person.getName() + ", Gender : " + person.getGender() + ", Marital Status : " + person.getMaritalStatus() + " ]");
		}
	}
}   