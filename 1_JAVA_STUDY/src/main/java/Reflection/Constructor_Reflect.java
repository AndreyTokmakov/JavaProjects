package Reflection;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/*
@ToString
@RequiredArgsConstructor()
class Person
{
    @NonNull
    private String firstName;

    private String secondName;

    @NonNull
    private int age;
}*/

class Person
{
    public Person(String name, int age) {}
    public Person(String name, String secondName, int age) {}
    public Person(String name, String secondName, String gender, int age) {}
}

public class Constructor_Reflect
{
    public static void main(String[] args)
    {
        Class<?> csl = Person.class;
        Constructor<?>[] constructors = csl.getConstructors();

        for (Constructor<?> constructor : constructors)
        {
            System.out.println("\nName    : " + constructor.getName());
            System.out.println("Accesses: " + Modifier.toString(constructor.getModifiers()));

            Class<?>[] paramTypes = constructor.getParameterTypes();
            for (final Class<?> pType: paramTypes)
            {
                System.out.println("\tType: " + pType);
            }
        }
    }
}
