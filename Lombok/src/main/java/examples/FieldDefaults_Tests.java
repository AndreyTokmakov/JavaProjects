package examples;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;
import lombok.experimental.NonFinal;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
class Person
{
    String firstname;
    String lastname;
}

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal=true)
class PersonFinal
{
    String firstname;
    String lastname;

    @NonFinal
    String email;
}

public class FieldDefaults_Tests
{


    public static void main(String[] args)
    {
        Person p = new Person("John", "Dow");
        PersonFinal fp = new PersonFinal("John", "Dow", "test@ya.ru");

        /** Will not compile Person::firstname is PRIVATE **/
        // System.out.println(p.firstname);
        // System.out.println(fp.firstname);
    }
}
