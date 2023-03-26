package tcp_tests;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class DifferentTests {
	
	private static void Test() throws NamingException {
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.dns.DnsContextFactory");
		// env.put(Context.PROVIDER_URL, "dns://8.8.8.8/");
		// env.put(Context.PROVIDER_URL,    "dns://server1.example.com/example.com");

		DirContext ictx = new InitialDirContext(env);
		Attributes attrs1 = ictx.getAttributes("www.ya.ru", new String[] {"A"});
		
		System.out.println(attrs1);
	}
	
	private static void DetermineIpAddress() {
		final String hostname = "www.ya.ru";

		try {
			InetAddress ipaddress = InetAddress.getByName(hostname);
			System.out.println("IP address: " + ipaddress.getHostAddress());
		} catch (final UnknownHostException exc) {
			System.out.println("Could not find IP address for: " + hostname);
		}
	}

	public static void main(String[] args) throws NamingException 
	{
		Test();
		// DetermineIpAddress();
	}
}
