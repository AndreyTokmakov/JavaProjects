
public class Test
{
    public static void main(String[] args) {
        interface Separator {
            void print(String text);
        }

        Separator divider = (item) -> System.out.println("**" + item + "**");

        divider.print("Divider");
    }
}
