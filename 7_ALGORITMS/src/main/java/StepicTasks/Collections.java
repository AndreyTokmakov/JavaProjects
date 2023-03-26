package StepicTasks;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Collections {


    public static void main(String[] args)
    {
        final Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            final String input = scanner.nextLine();
            final List<String> parts = new ArrayList<String>();
            int pos = 0, end = 0, count = 0;
            while ((end = input.indexOf(' ', pos)) >= 0) {
                if (1 == count++ % 2)
                    parts.add(input.substring(pos, end));
                pos = end + 1;
            }
            if (1 == count++ % 2)
                parts.add(input.substring(pos, input.length()));

            for (int i = parts.size() - 1; i >= 0; --i)
                System.out.print(parts.get(i) + " ");
        }
    }
}
