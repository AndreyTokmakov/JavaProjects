package examples;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class Base
{

}

@RequiredArgsConstructor
class Derived extends Base
{
    private final Integer value;
    private final String str;
}

public class Required_Arg_Constructor
{



    public static void main(String[] args)
    {
        Derived d = new Derived(1, "");

    }
}
