/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Person builder pattern demo
*
* @name    : ObeserverDemo.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 23, 2020
****************************************************************************/ 

package Builder;

public class Person_NestedClass2 {
	
	public static void main(String[] args) 
	{
		Person2 person = new Person2.Builder()
				.givenName("Bob")
	            .surName("Baker")
	            .age(21)
	            .gender(Person2.Gender.MALE)
	            .email("bob.baker@example.com")
	            .phoneNumber("201-121-4678")
	            .address("44 4th St, Smallville, KS 12333")
	            .build();
		
		System.out.println(person);
	}
}



/** Person2 class: **/
class Person2 {
	private String givenName;
	private String surName;
	private int age;
	private Gender gender;
	private String eMail;
	private String phone;
	private String address;

	enum Gender {
		MALE,
		FEMALE
	};

	public static class Builder {
		private final Person2 person = new Person2();
	       
	    public Builder givenName(String givenName){
			person.givenName = givenName;
	    	return this;
	    }
	    
	    public Builder surName(String surName){
			person.surName = surName;
	    	return this;
	    }
	    
	    public Builder age (int val){
			person.age = val;
	    	return this;
	    }
	    
	    public Builder gender(Gender val){
			person.gender = val;
	    	return this;
	    }
	    
	    public Builder email(String val){
			person.eMail = val;
	    	return this;
	    }
	    
	    public Builder phoneNumber(String val){
			person.phone = val;
	    	return this;
	    }
	    
	    public Builder address(String val){
			person.address = val;
	    	return this;
	    }
	    
	    public Person2 build(){
	    	return person;
	    }
	}

	private Person2(){
		super();
	}

	@Override
	public String toString() {
	    return "Name: " + givenName + " " + surName + "\n" +
				"Age: " + age +
				"  Gender: " + gender + "\n" + "eMail: " + eMail + "\n";
	}
}
