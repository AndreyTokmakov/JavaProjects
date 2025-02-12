package Strings;

public class TextBlocks
{
    public static void main(String[] args)
    {
        String poem = """
                      I would want to establish strength; root-like,
                      anchored in the hopes of solidity.
                      Forsake the contamination of instability.
                      Prove I'm the poet of each line of prose.""";


        System.out.println(poem + "\n");

        poem = poem.indent(6);

        System.out.println(poem+ "\n");

        poem = poem.stripTrailing();

        System.out.println(poem+ "\n");
    }
}
