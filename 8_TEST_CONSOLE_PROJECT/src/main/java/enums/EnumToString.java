package enums;

public class EnumToString
{
    public enum Style
    {
        BOLD,
        ITALIC,
        UNDERLINE,
        STRIKETHROUGH
    }

    static void ToStringTests()
    {
        {
            String styleStr = Style.BOLD.toString();
            System.out.println(styleStr);
        }

        {
            String styleStr = Style.BOLD.name();
            System.out.println(styleStr);
        }
    }

    static void FromStringTests()
    {
        Style style = Style.valueOf("Bold");
        System.out.println(style);
    }

    public static void main(String[] args)
    {
        ToStringTests();
        FromStringTests();
    }
}
