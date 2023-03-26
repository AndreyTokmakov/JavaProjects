package Security;

import java.util.*;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/** **/
class CryptoUtils {
	
    public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }
	
	protected static SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
	    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
	    keyGen.init(keysize, SecureRandom.getInstanceStrong());
	    return keyGen.generateKey();
	}
	
	protected static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) throws  NoSuchAlgorithmException, InvalidKeySpecException {
	    SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	    // iterationCount = 65536
	    // keyLength = 256
	    KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
	    SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
	    return secret;
	}
	
	 // hex representation
    public static String hex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    
    // print hex with block size split
    public static String hexWithBlockSize(byte[] bytes, int blockSize) {
        String hex = hex(bytes);

        // one hex = 2 chars
        blockSize = blockSize * 2;

        // better idea how to print this?
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < hex.length()) {
            result.add(hex.substring(index, Math.min(index + blockSize, hex.length())));
            index += blockSize;
        }
        return result.toString();
    }
}

/**
 * AES-GCM inputs - 12 bytes IV, need the same IV and secret keys for encryption and decryption.
 * The output consist of iv, password's salt, encrypted content and auth tag in the following format:
 * output = byte[] {i i i s s s c c c c c c ...}
 * i = IV bytes
 * s = Salt bytes
 * c = content bytes (encrypted content)
 */
class EncryptorAesGcm {
	private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    @SuppressWarnings("unused")
	private static final int AES_KEY_BIT = 256;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    
    protected static final CryptoUtils utils = new CryptoUtils();

    // AES-GCM needs GCMParameterSpec
    public byte[] encrypt(byte[] pText, SecretKey secret, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] encryptedText = cipher.doFinal(pText);
        return encryptedText;
    }

    // prefix IV length + IV bytes to cipher text
    public byte[] encryptWithPrefixIV(byte[] pText, SecretKey secret, byte[] iv) throws Exception {
        byte[] cipherText = encrypt(pText, secret, iv);
        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
        									.put(iv)
        									.put(cipherText)
        									.array();
        return cipherTextWithIv;
    }

    public String decrypt(byte[] cText, SecretKey secret, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
        byte[] plainText = cipher.doFinal(cText);
        return new String(plainText, UTF_8);
    }

    public String decryptWithPrefixIV(byte[] cText, SecretKey secret) throws Exception {
        ByteBuffer bb = ByteBuffer.wrap(cText);
        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        //bb.get(iv, 0, iv.length);
        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);
        String plainText = decrypt(cipherText, secret, iv);
        return plainText;
    }
    
	protected SecretKey getAESKey() throws NoSuchAlgorithmException {
	    return CryptoUtils.getAESKey(AES_KEY_BIT);
	}
	
    public byte[] getRandomNonce() {
    	return CryptoUtils.getRandomNonce(IV_LENGTH_BYTE);
    }
}


class Tester {
	protected void KeyPairGenerator( ) {
        try {   	  
            // creating the object of KeyPairGenerator 
        	KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA"); 
  
            // initializing with 1024 
            kpg.initialize(1024); 
  
            KeyPair kp = kpg.genKeyPair(); 
            System.out.println("Keypair : " + kp); 
            System.out.println("Public : "  + kp.getPublic()); 
            System.out.println("Private : " + kp.getPrivate()); 
            //System.out.println("Private : " + kp.getAlgorithm()); 
        } 
        catch (NoSuchAlgorithmException e) { 
            System.out.println("Exception thrown : " + e); 
        } 
	}
	
	protected void Signature() {
        try { 
            // creating the object of Signature 
            Signature sr = Signature.getInstance("SHA1withDSA"); 
  
            // getting the providerby using method getProvider() 
            Provider provider = sr.getProvider(); 
  
            // printing the provider name 
            System.out.println("Provider : " + provider); 
        }  catch (NoSuchAlgorithmException e) { 
        	System.out.println("Exception thrown : " + e); 
        } 
	}
	
    public void AES_Encryption_Decryption() throws Exception  {
    	EncryptorAesGcm aesGcm = new EncryptorAesGcm();
    	
        String OUTPUT_FORMAT = "%-30s:%s";
        String pText = "Hello World AES-GCM, Welcome to Cryptography!";
        SecretKey secretKey = aesGcm.getAESKey();

        // encrypt and decrypt need the same IV. AES-GCM needs IV 96-bit (12 bytes)
        byte[] iv = aesGcm.getRandomNonce();

        byte[] encryptedText = aesGcm.encryptWithPrefixIV(pText.getBytes(StandardCharsets.UTF_8), secretKey, iv);

        System.out.println("\n------ AES GCM Encryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input (plain text)", pText));
        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));
        System.out.println(String.format(OUTPUT_FORMAT, "IV  (hex)", CryptoUtils.hex(iv)));
        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) ", CryptoUtils.hex(encryptedText)));
        System.out.println(String.format(OUTPUT_FORMAT, "Encrypted (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));

        System.out.println("\n------ AES GCM Decryption ------");
        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex)", CryptoUtils.hex(encryptedText)));
        System.out.println(String.format(OUTPUT_FORMAT, "Input (hex) (block = 16)", CryptoUtils.hexWithBlockSize(encryptedText, 16)));
        System.out.println(String.format(OUTPUT_FORMAT, "Key (hex)", CryptoUtils.hex(secretKey.getEncoded())));

        String decryptedText = aesGcm.decryptWithPrefixIV(encryptedText, secretKey);

        System.out.println(String.format(OUTPUT_FORMAT, "Decrypted (plain text)", decryptedText));

    }
}



public class Security_API_Tests {
	public static void main(String[] args) throws Exception {
		Tester tester = new Tester();
		
		// tester.KeyPairGenerator();
		// tester.Signature();
		tester.AES_Encryption_Decryption();
	}
}
