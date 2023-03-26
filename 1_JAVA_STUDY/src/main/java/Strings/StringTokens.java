package Strings;

import java.util.List;
import java.util.StringTokenizer;
import java.util.Collections;
import java.util.stream.Collectors;

public class StringTokens {

	public void Tokens1() {
		String str = "I am sample string and will be tokenized on space";
		StringTokenizer defaultTokenizer = new StringTokenizer(str);
		System.out.println("Total number of tokens found : " + defaultTokenizer.countTokens());
		 
		while (defaultTokenizer.hasMoreTokens()){
		    System.out.println(defaultTokenizer.nextToken());
		}
		System.out.println("Total number of tokens found : " + defaultTokenizer.countTokens());	
	}

	public void Tokens1_AsList() {
		String str = "I am sample string and will be tokenized on space";
		StringTokenizer defaultTokenizer = new StringTokenizer(str);

		System.out.println("Total number of tokens found : " + defaultTokenizer.countTokens());

		List<String> list = Collections.list(new StringTokenizer(str)).stream()
				.map(token -> (String) token).toList();

		for (var s: list)
			System.out.print("[ " + s + " ] ");
		System.out.println();
	}

	
	public void Test2() {
		String url = "https://howtodoinjava.com/java-initerview-ques+tions";
		StringTokenizer multiTokenizer = new StringTokenizer(url, "://.-+");
		while (multiTokenizer.hasMoreTokens())
		{
		    System.out.println(multiTokenizer.nextToken());
		}
	}
	
	
	public void TEST() {
		String s = "He is a very very good boy, isn't he?";
		StringTokenizer tokens = new StringTokenizer(s, "!,?._' @");

		System.out.println(tokens.countTokens());
		while (tokens.hasMoreTokens())
			System.out.println(tokens.nextToken());
	}



	public void TEST_1()
	{
		String header = "IN-USE  BSSID              SSID           MODE   CHAN  RATE        SIGNAL  BARS  SECURITY";
		String line1 = "        A0:95:7F:39:34:6D  Alina          Infra  2     260 Mbit/s  100     ▂▄▆█  WPA";
		String line2 = "*       A0:95:7F:39:34:71  Alina-5G       Infra  44    540 Mbit/s  79      ▂▄▆_  WPA";

		StringTokenizer tokensHeader = new StringTokenizer(header);
		StringTokenizer tokens1 = new StringTokenizer(line1);
		StringTokenizer tokens2 = new StringTokenizer(line2);

		System.out.println(tokensHeader.countTokens());
		System.out.println(tokens1.countTokens());

		System.out.println(tokens2.countTokens());

		while (tokens2.hasMoreTokens())
			System.out.println("[" + tokens2.nextToken() + "]");
	}
	
	public static void main(String[] args) {
		StringTokens tests = new StringTokens();
		
		// tests.Tokens1();
		tests.Tokens1_AsList();


		// tests.Test2();
		// tests.TEST();
		// tests.TEST_1();
	}
}
