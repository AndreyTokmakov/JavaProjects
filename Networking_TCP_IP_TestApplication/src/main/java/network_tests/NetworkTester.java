package network_tests;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkTester {
	
	protected void GetHostAddress() throws UnknownHostException {
		String hostName = "www.ya.ru";
		String address = InetAddress.getByName(hostName).getHostAddress();
		System.out.println(address);
	}
	
	protected void GetLocalHost() throws UnknownHostException {
		String address = InetAddress.getLocalHost().getHostAddress();
		System.out.println(address);
	}

	public static void main(String[] args) throws UnknownHostException {
		NetworkTester tester = new NetworkTester();
		// tester.GetLocalHost();
		tester.GetHostAddress();
	}
}
