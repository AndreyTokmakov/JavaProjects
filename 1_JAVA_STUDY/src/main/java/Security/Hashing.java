package Security;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

class HashingTester
{
    private final static byte[] emptyByteArray = new byte[0];
    private static final String HASH_ALGORITHM = "HmacSHA512";

	public String encryptThisString(String input) 
    { 
        try { 
            // getInstance() method is called with algorithm SHA-224 
            MessageDigest md = MessageDigest.getInstance("SHA-512");
  
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

    public void encryptStringTest()
    {
        String s1 = "GeeksForGeeks";
        System.out.println(s1 + " : " + this.encryptThisString(s1));

        String s2 = "Hello world";
        System.out.println(s2 + " : " + this.encryptThisString(s2));
    }

    public void updateTest()
    {
        String input = null;
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-512");

            //byte[] data = input.getBytes(StandardCharsets.UTF_8);

            byte[] data = null == input ? new byte[0] : input.getBytes(StandardCharsets.UTF_8);
            digest.update(data);

            System.out.println(Hex.encodeHexString(digest.digest()));

        } catch (NoSuchAlgorithmException e) {  // For specifying wrong message digest algorithms
            throw new RuntimeException(e);
        }
    }

    public void testAlgo_HA512() throws NoSuchAlgorithmException, InvalidKeyException
    {
        final String message = "hello world";
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.update(message.getBytes(StandardCharsets.UTF_8));
            System.out.println(Hex.encodeHexString(digest.digest()));

        } catch (NoSuchAlgorithmException e) {  // For specifying wrong message digest algorithms
            throw new RuntimeException(e);
        }
    }

    public void testAlgo_HmacSHA512_With_Secret() throws NoSuchAlgorithmException, InvalidKeyException
    {
        final String secret = "12345";
        final String message = "hello world";

        Mac hacSha512 = Mac.getInstance(HASH_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HASH_ALGORITHM);
        hacSha512.init(secretKeySpec);

        byte[] bytes = hacSha512.doFinal(message.getBytes(StandardCharsets.UTF_8));
        System.out.println(Hex.encodeHexString(bytes));
    }

    public void testAlgo_HmacSHA512_WithOut_Secret() throws NoSuchAlgorithmException, InvalidKeyException
    {
        final String secret = "12345";
        final String message = "hello world";

        Mac hacSha512 = Mac.getInstance(HASH_ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HASH_ALGORITHM);
        SecretKeySpec secretKeySpec2 = new SecretKeySpec(emptyByteArray, HASH_ALGORITHM);
        hacSha512.init(secretKeySpec);


        byte[] bytes = hacSha512.doFinal(message.getBytes(StandardCharsets.UTF_8));
        System.out.println(Hex.encodeHexString(bytes));
    }
}

public class Hashing
{
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException
    {
		HashingTester tester = new HashingTester();
        // tester.encryptStringTest();
        // tester.updateTest();


        // tester.testAlgo_HA512();

        tester.testAlgo_HmacSHA512_With_Secret();
        tester.testAlgo_HmacSHA512_WithOut_Secret();
	}
}
