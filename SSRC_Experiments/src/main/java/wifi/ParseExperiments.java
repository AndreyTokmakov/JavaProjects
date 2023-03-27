package wifi;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class ParseExperiments
{
    public static void TEST_1()
    {
        String header = "IN-USE  BSSID              SSID           MODE   CHAN  RATE        SIGNAL  BARS  SECURITY";
        String line1 = "        A0:95:7F:39:34:6D  Alina          Infra  2     260 Mbit/s  100     ▂▄▆█  WPA";
        String line2 = "*       A0:95:7F:39:34:71  Alina-5G       Infra  44    540 Mbit/s  79      ▂▄▆_  WPA";


        List<String> tokens = new java.util.ArrayList<>(Collections.list(new StringTokenizer(line2)).stream()
                .map(token -> (String) token).toList());

        String first = tokens.get(0);
        if (first.contains("*") && !first.contains(":")) {
            System.out.println("Connected to '" + tokens.get(2) + "' Access Point");
        }




        /*
        int pos = -1;
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).contains("t/s")) {
                pos = i;
                break;
            }
        }

        list.set(pos - 1, list.get(pos - 1) + " " + list.get(pos));
        for (; pos < list.size() - 1; ++pos)
            list.set(pos, list.get(pos + 1));
        list.remove(list.size() - 1);
        */


        /*
        for (var s: list)
            System.out.print("[ " + s + " ] ");
        System.out.println();
        */
    }

    public static void main(String[] args)
    {
        TEST_1();
    }
}
