package Networking;

import java.io.IOException;
import java.net.URL;

class Tester {
	public void URLParams() {
		try {
			URL url = new URL("https://www.amrood.com/index.htm?language=en#j2se");
	         
			System.out.println("URL: " + url.toString());
			System.out.println("protocol: " + url.getProtocol());
			System.out.println("authority: " + url.getAuthority());
			System.out.println("file: " + url.getFile());
			System.out.println("host: " + url.getHost());
			System.out.println("path(: " + url.getPath());
			System.out.println("port: " + url.getPort());
			System.out.println("default port: " + url.getDefaultPort());
			System.out.println("query: " + url.getQuery());
			System.out.println("ref: " + url.getRef());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

public class URL_Tests {
	public static void main(String[] args) {
		Tester tester = new Tester();
		tester.URLParams();

	}
}
