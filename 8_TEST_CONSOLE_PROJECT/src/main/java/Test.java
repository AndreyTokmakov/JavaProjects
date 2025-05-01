import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

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

    private static List<String> makeHeader(List<String> accounts) {
        return Stream.of(List.of("Date", "Asset"), accounts).flatMap(List::stream).toList();
    }

    public static void main(String[] args)
    {
        // interfaceTest();
        // Lombok_Getter_Setter();

        List<String> accounts = List.of("OKX", "Binance");

        System.out.println(accounts);


        //List<String> header = makeHeader(accounts);
        //System.out.println(header);
    }
}
