
public class NewFeaturesTests
{
    public static void main(String[] args)
    {
        final int state = 2;

        final String result = switch (state)  {
            case 1 -> "One";
            case 2 -> "Two";
            case 3 -> "Three";
            default -> "Other";
        };

        System.out.println(result);
    }
}
