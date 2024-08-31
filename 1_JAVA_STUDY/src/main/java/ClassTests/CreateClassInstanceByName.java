package ClassTests;

import lombok.Data;
import lombok.NonNull;

import java.lang.reflect.Constructor;

@Data
class Person
{
    @NonNull
    private String firstName;

    @NonNull
    private String secondName;

    public void introduce() {
        System.out.println("Hello, my name is " + firstName + " " + secondName);
    }
}

public class CreateClassInstanceByName
{
    private static final String className = "ClassTests.Person";

    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException
    {
        try {
            // Load the class dynamically
            Class<?> clazz = Class.forName(className);

            // Get the constructor with a String parameter
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class);

            // Create an instance by invoking the constructor
            Object personObject = constructor.newInstance("John", "Doe");

            // Cast the object to the actual class
            Person johnDoe = (Person) personObject;

            // Call a method on the created object
            johnDoe.introduce();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
