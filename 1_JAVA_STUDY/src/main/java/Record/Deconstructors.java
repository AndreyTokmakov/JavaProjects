package Record;

public class Deconstructors
{
    public static record Name(String fName, String lName, int age) {};

    public static void main(String[] args)
    {
        // Java --> 21
        /*
        if(host instanceof Name(String fName, String lName, int age)) {
            String printName = lName + ", " + fName + " age: " + age;
        }
        */
    }
}
