package Lists;

import java.util.ArrayList;
import java.util.Arrays;

class Solution 
{
	private final java.util.Scanner input = new java.util.Scanner(System.in); 

    protected void Test() {
        int count = Integer.valueOf(input.nextLine());
        ArrayList<ArrayList<String>> lists = new ArrayList<ArrayList<String>>();
        while (0 != count--){
            String line = input.nextLine();
            ArrayList<String> str_list = new ArrayList<String>(Arrays.asList(line.split(" ")));
            str_list.remove(0);
            lists.add(str_list);
        }

        count = Integer.valueOf(input.nextLine());
        while (0 != count--) {
            String line = input.nextLine();
            String[] queries = line.split(" ");
            if (2 == queries.length) {
                int n1 = Integer.valueOf(queries[0]);
                int n2 = Integer.valueOf(queries[1]);
                if (n1 <= lists.size() && n2 <= lists.get(n1- 1).size())
                {
                    System.out.println(lists.get(n1- 1).get(n2- 1));
                } else {
                    System.out.println("ERROR!");
                }
            } else {
                System.out.println("ERROR!");
            }
        }
    }
}

public class Arraylist {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
