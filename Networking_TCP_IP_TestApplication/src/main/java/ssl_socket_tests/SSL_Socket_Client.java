/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* Java SSLSocket TLS 1.3 example
*
* @name    : SSL_Socket_Client.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : October 20, 2020
****************************************************************************/ 

package ssl_socket_tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

final class SSLSocketClient {
	/** Protocols list: **/
	private static final String[] protocols = new String[]{"TLSv1.3"};
	
	/** Cipher suites: **/
    private static final String[] cipher_suites = new String[]{"TLS_AES_128_GCM_SHA256"};
    
    public void sendRequest() throws Exception {
        SSLSocket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
            socket = (SSLSocket) factory.createSocket("google.com", 443);
            
            socket.setEnabledProtocols(protocols);
            socket.setEnabledCipherSuites(cipher_suites);
            socket.startHandshake();

            out = new PrintWriter(new BufferedWriter( new OutputStreamWriter(socket.getOutputStream())));

            out.println("GET / HTTP/1.0");
            out.println();
            out.flush();

            if (out.checkError()) {
                System.out.println("SSLSocketClient:  java.io.PrintWriter error");
            }

            /* read response */
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                System.out.println(inputLine);

        } catch (final Exception exc) {
        	exc.printStackTrace();
        } finally {
            if (socket != null)
                socket.close();
            if (out != null)
                out.close();
            if (in != null)
                in.close();
        }
    }
} 

public class SSL_Socket_Client {
	public static void main(String[] args) throws Exception {
		SSLSocketClient client = new SSLSocketClient();
		client.sendRequest();

	}
}
