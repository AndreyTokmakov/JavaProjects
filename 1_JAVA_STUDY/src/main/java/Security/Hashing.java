package Security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashingTester {
	public String encryptThisString(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-224 
            MessageDigest md = MessageDigest.getInstance("SHA-224"); 
  
            // digest() method is called to calculate message digest of the input string returned as array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
  
            // Add preceding 0s to make it 32 bit 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 

            return hashtext; 
        } catch (NoSuchAlgorithmException e) {  // For specifying wrong message digest algorithms 
            throw new RuntimeException(e); 
        } 
    } 
}

public class Hashing {
	public static void main(String[] args) {
		HashingTester tester = new HashingTester();
		
		String s1 = "GeeksForGeeks"; 
		System.out.println(s1 + " : " + tester.encryptThisString(s1)); 
	  
		String s2 = "Hello world"; 
		System.out.println(s2 + " : " + tester.encryptThisString(s2));
	}
}
