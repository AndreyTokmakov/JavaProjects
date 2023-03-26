//============================================================================
//Name        : Utilities.java
//Created on  : Aug 14, 2017
//Author      : Tokmakov Andrey
//Version     :
//Copyright   : Your copyright notice
//Description :
//============================================================================

package viewer.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.BadLocationException;

/** @author tokmakov **/
/** @class  Utilities **/
//@SuppressWarnings("unused")
public class Utilities {

	public Utilities() {
		// TODO Auto-generated constructor stub
	}
	
	public static String ntoa(long raw) {
	    byte[] b = new byte[] {(byte)(raw >> 24), (byte)(raw >> 16), (byte)(raw >> 8), (byte)raw};
	    try {
	        return InetAddress.getByAddress(b).getHostAddress();
	    } catch (UnknownHostException e) {
	        return null;
	    }
	}

	public static String AnalyzeFines(javax.swing.JTextArea responseField) {
		String result = "";
		int p1, p2;
		Long ip, fine;
		
		Map<Long, Long> finedIps = new HashMap<Long, Long>();
		for (String line : responseField.getText().split("\n")) {
			if (line.contains("\"blklist\"") && line.contains("\"fine\":")) {
				p1 = line.indexOf("\"ip\":");
				p2 = line.indexOf(",", p1 + 5);
				ip = Long.valueOf(line.substring(p1 + 5, p2).trim());
				
				p1 = line.indexOf("\"fine\":", p2);
				p2 = line.indexOf(",", p1 + 7);
				fine = Long.valueOf(line.substring(p1 + 7, p2).trim());
				
				if (true == finedIps.containsKey(ip)){
					fine = finedIps.get(ip) + fine;
				} 
				finedIps.put(ip, fine);
			}
		}
		if (finedIps.size() >= 1) {
			result = "IP's count = " + finedIps.size() + "\n";
			for(Map.Entry<Long, Long> entry : finedIps.entrySet())
				result += ntoa(entry.getKey()) + " == " + entry.getValue() + "\n";
		}
		return result;
	}
}
