package SSL;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.KeyGenerator;

import org.apache.commons.io.IOUtils;

public class SSLKeys {
	
	public void KeyTests() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
		//KeyPair keyPair = new KeyPair(null, null);
		
		
		/*
        File file1 = new File("R:\\Documents\\sshKeys\\Infrastructure_key\\id_rsa.pub");
        FileInputStream fis1 = new FileInputStream(file1);
        DataInputStream dis1 = new DataInputStream(fis1);
        byte[] keyBytes1 = new byte[(int) file1.length()];
        dis1.readFully(keyBytes1);
        dis1.close();

        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyBytes1);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) factory.generatePublic(publicKeySpec);

        System.out.println("Exponent :" + pubKey.getPublicExponent());
        System.out.println("Modulus" + pubKey.getModulus());
        */
		
		File pubKeyFile = new File("R:\\Documents\\sshKeys\\Infrastructure_key\\id_rsa.pub");
		File privKeyFile = new File("R:\\Documents\\sshKeys\\Infrastructure_key\\private_key.ppk");
		
		DataInputStream dis = new DataInputStream(new FileInputStream(pubKeyFile));
        byte[] pubKeyBytes = new byte[(int)pubKeyFile.length()];
        dis.readFully(pubKeyBytes);
        dis.close();
        
        // read private key DER file
        dis = new DataInputStream(new FileInputStream(privKeyFile));
        byte[] privKeyBytes = new byte[(int)privKeyFile.length()];
        dis.read(privKeyBytes);
        dis.close();
        
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        
        // decode public key
        X509EncodedKeySpec pubSpec = new X509EncodedKeySpec(pubKeyBytes);
        RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(pubSpec);
        
        // decode private key
        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(privKeyBytes);
        RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(privSpec);
	}
	
	public PublicKey getPublicKey(final String base64PublicKey) {
		PublicKey publicKey = null;
		try{
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}
	
	public PublicKey getPublicKey3(String file) throws IOException {
		Path path = Paths.get(file);
		byte[] bytes = Files.readAllBytes(path);
		PublicKey publicKey = null;
		try{
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			publicKey = keyFactory.generatePublic(keySpec);
			return publicKey;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return publicKey;
	}

	
	public void KeyTests2() throws IOException  {
		final String publicKeyFile = "R:\\Documents\\sshKeys\\Infrastructure_key\\id_rsa.pub";
		FileReader fileReader = new FileReader(publicKeyFile);
		final String publicKey = IOUtils.toString(fileReader);
		
		System.out.println(publicKey);
		
		this.getPublicKey(publicKey);
	}
	
	//////////////////////////////////////////////////////
	
	
	public void GenerateKeys() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		int keyBitSize = 256;

		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(keyBitSize, secureRandom);
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
	
		PrivateKey privateKey = keyPair.getPrivate();
		PublicKey publicKey = keyPair.getPublic();
		
		final Base64.Encoder encoder = Base64.getEncoder();
		final String publicKeyStr = encoder.encodeToString(publicKey.getEncoded());
		final String privateKeyStr = encoder.encodeToString(privateKey.getEncoded());
		
		
		System.out.println(publicKeyStr);
		System.out.println(publicKey);
		
		
		PublicKey publicKey2 = getPublicKey(publicKeyStr);
		final String publicKeyStr2 = encoder.encodeToString(publicKey2.getEncoded());


		System.out.println(publicKeyStr2);
		System.out.println(publicKey2);
	}

	public void TestKeys()
	{
		// final String publicStrOrigin = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlVgPempiojRJNjCD+z2ad
		// TIU9O6FzWjb83tY3T3dsRrIbPkm0SZGrokzf2YHHIXLZrMjTw3QYNH6QfcsCLzExUvgHEs5sn7M2UHLv8T7tG7S5eiCxOLdxK
		// 7iGA3jMrUtGQRq55dK9pe2P5v4JW8OnWzImb9nL5Oq8970UXK4hceuGD1y5VbPYSVl3ffYNQ78RZa4qA9vY+DfChUN0p+sEjn
		// dfSm8YSWPTTePQcRfe2wQXagMyHAbBLDKpIvo/V4S41RXUACYhM5ukudLrdSMDem5N7YR8reJ2yxtbZUqdNs02HhjAfsGnwbb
		// kOKGWAlq1rwVAKPUystC7uMst3e/NwIDAQAB";

		final String publicStrOrigin = "AAAAB3NzaC1yc2EAAAADAQABAAABAQDqHPjwbjs14b12gSFdeDNiE9cvqrY94kEjhQJZC" +
				"ljfhEapU5O5mDcBqyRbJ2ZYzZrEiyIvCJDQrBzrWjJzt+bBZntzSbmVGhf31DScIKUqzox3mMN5Wxy3JTctiC/PCNQdTj" +
				"Qhsm6AtsOlLsZs3dKdk1NHs5MYSOwU6ioEwBSFhUrcN7qHp/tOIbWYb2Jd81mtJkLs0fIGqqJhjbrHr1wxGetZRRJCTwsO" +
				"04h5d+olzQSh4R4W9eVXlQmSzHz0/GelpfC2EA07U25xR+OkBpcH2wvmtMo01fq+ufAkuTgP36G0SMpjEvmWLD8um5vt" +
				"IZfykYqm/26i4dpnQWFvjsE7";
		
		
		final Base64.Encoder encoder = Base64.getEncoder();
		PublicKey publicKey = getPublicKey(publicStrOrigin);
		final String publicKeyStr = encoder.encodeToString(publicKey.getEncoded());
		
		
		System.out.println(publicStrOrigin);
		

		System.out.println(publicKey);
		System.out.println(publicKeyStr);
	}
	
	/*************** MAIN  
	 * @throws IOException **************/
	
	/* @throws NoSuchAlgorithmException */
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		SSLKeys tests = new SSLKeys();
		
		// tests.GenerateKeys();
		// tests.getPublicKey3("R:\\Projects\\Java\\JavaStudyProject\\src\\SSL\\public.key");
		tests.TestKeys();
	}
}
