package Strings;

import java.text.ChoiceFormat;
import java.text.MessageFormat;

public class Formatting_ChoiceFormat
{
    public static void main(String[] args)
    {
        MessageFormat form = new MessageFormat("Я могу {1} {0}.");
        int count = 2;
        String exercise = "подтянуться";
        Object[] testArgs = {count, exercise};

        double[] fileLimits = {0,2,5};
        String[] filePart = {"{0} раз","{0} раза","{0} раз"};
        ChoiceFormat fileform = new ChoiceFormat(fileLimits, filePart);
        form.setFormatByArgumentIndex(0, fileform);

        System.out.println(form.format(testArgs));
    }
}
