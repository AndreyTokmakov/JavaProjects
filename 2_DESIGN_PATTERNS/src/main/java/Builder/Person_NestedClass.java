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

public class Person_NestedClass {
	
	public static void main(String[] args) 
	{
		Person person = new Person.Builder()
				.givenName("Bob")
	            .surName("Baker")
	            .age(21)
	            .gender(Gender.MALE)
	            .email("bob.baker@example.com")
	            .phoneNumber("201-121-4678")
	            .address("44 4th St, Smallville, KS 12333")
	            .build();
		
		System.out.println(person);
	}
}

enum Gender { 
	MALE,
	FEMALE
};

/** Person class: **/
class Person {
	private String givenName;
	private String surName;
	private int age;
	private Gender gender;
	private String eMail;
	private String phone;
	private String address;
	  
	public static class Builder {
		private String givenName="";
	    private String surName="";
	    private int age = 0;
	    private Gender gender = Gender.FEMALE;
	    private String eMail = "";
	    private String phone = "";
	    private String address = "";
	       
	    public Builder givenName(String givenName){
	    	this.givenName = givenName;
	    	return this;
	    }
	    
	    public Builder surName(String surName){
	    	this.surName = surName;
	    	return this;
	    }
	    
	    public Builder age (int val){
	    	age = val;
	    	return this;
	    }
	    
	    public Builder gender(Gender val){
	    	gender = val;
	    	return this;
	    }
	    
	    public Builder email(String val){
	    	eMail = val;
	    	return this;
	    }
	    
	    public Builder phoneNumber(String val){
	    	phone = val;
	    	return this;
	    }
	    
	    public Builder address(String val){
	    	address = val;
	    	return this;
	    }
	    
	    public Person build(){
	    	return new Person(this);
	    }
	}
	    
	private Person(){
		super();
	}
	    
	private Person(Builder builder){
		givenName = builder.givenName;
	    surName = builder.surName;
	    age = builder.age;
	    gender = builder.gender;
	    eMail = builder.eMail;
	    phone = builder.phone;
	    address = builder.address;
	}
	  
	public String getGivenName(){
	    return givenName;
	}
	  
	public String getSurName(){
	    return surName;
	}
	  
	public int getAge(){
	    return age;
	}
	  
	public Gender getGender(){
	    return gender;
	}
	  
	public String getEmail(){
	    return eMail;
	}
	  
	public String getPhone(){
	    return phone;
	}
	  
	public String getAddress(){
	    return address;
	}

	@Override
	public String toString() {
	    return "Name: " + givenName + " " + surName + "\n" +
				"Age: " + age +
				"  Gender: " + gender + "\n" + "eMail: " + eMail + "\n";
	}
}
