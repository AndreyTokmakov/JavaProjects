import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

public class Test
{

    private final static class ImmutableObject
    {
        @Getter @Setter
        private String name;
    }

    public static void Lombok_Getter_Setter()
    {
        ImmutableObject obj = new ImmutableObject();
        obj.setName("GGGG");
        System.out.println(obj.getName());
    }

    public static void interfaceTest()
    {
        interface Separator {
            void print(String text);
        }

        Separator divider = (item) -> System.out.println("**" + item + "**");
        divider.print("Divider");
    }


    protected interface IObject
    {
        void info();
    }

    private static final class ObjectImpl implements IObject
    {
        @Override
        public void info() {

        }
    }

    public static void main(String[] args)
    {
        // interfaceTest();
        // Lombok_Getter_Setter();

        ObjectImpl obj = new ObjectImpl();
        if (obj instanceof IObject)
        {

        }
    }
}
