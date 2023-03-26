//============================================================================
// Name        : Client.java
// Created on  : August 30, 2020
// Author      : Tokmakov Andrey
// Version     : 0.1
// Copyright   : Your copyright notice
// Description : Proxy design pattern demo
//============================================================================

package Proxy;

import java.util.ArrayList;
import java.util.List;

// A very simple real life scenario is our college internet, which restricts
// few site access. The proxy first checks the host you are connecting to, if it
// is not part of restricted site list, then it connects to the real internet. 
// This example is based on Protection proxies.

/** Internet interface. **/
interface Internet  { 
	public void connectTo(String serverHost) throws Exception;
}

/** RealInternet class. **/
class RealInternet implements Internet { 
	@Override
	public void connectTo(String serverHost) {
		System.out.println("Connecting to "+ serverHost);
	} 
} 

/** ProxyInternet class. **/
class ProxyInternet implements Internet { 
	private final Internet internet = new RealInternet();
	private static final List<String> bannedSites;
	
	static { 
		bannedSites = new ArrayList<String>(); 
		bannedSites.add("abc.com"); 
		bannedSites.add("pornhub.com");
		bannedSites.add("ijk.com"); 
		bannedSites.add("lnm.com"); 
	} 
	
	@Override
	public void connectTo(String serverHost) throws Exception {
		if (bannedSites.contains(serverHost.toLowerCase())) {
			throw new Exception(String.format("Access Denied to resource '%s'", serverHost));
		} 	
		internet.connectTo(serverHost);
	} 
} 


public class Client {
	public static void main (String[] args) 
	{ 
		Internet internet = new ProxyInternet(); 
		try { 
			internet.connectTo("geeksforgeeks.org"); 
			internet.connectTo("pornhub.com");
		} 
		catch (Exception e)  { 
			System.out.println(e.getMessage()); 
		} 
	} 
}
