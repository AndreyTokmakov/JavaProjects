package ssl_socket_tests;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SSLSocketServer
{
    private static final int serverPort = 52525;

    static class Acceptor implements Runnable {
        private final SSLSocket socket;

        public Acceptor(SSLSocket socket) {
            this.socket = socket;
        }

        public void service() {
            Thread thread = new Thread(this);
            thread.start();
        }

        @Override
        public void run() {
            try {
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputstreamreader = new InputStreamReader(inputStream);
                BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

                String string = null;
                while ((string = bufferedreader.readLine()) != null) {
                    System.out.println(string);
                    System.out.flush();
                }
            } catch (Exception e) {
                // replace with other code
                e.printStackTrace();
            }
        }
    }

    // TODO: Create JKS
    // keytool -genkey -keystore keystore.jks -alias ssl -keyalg RSA -sigalg SHA256withRSA -validity 365 -keysize 2048

    public static void main(String[] args) throws Exception
    {
        String keyStoreFile = "/home/andtokm/DiskS/ProjectsUbuntu/SSL/keystore.jks";

        System.setProperty("javax.net.debug", "ssl,handshake");
        System.setProperty("javax.net.ssl.keyStore", keyStoreFile);
        System.setProperty("javax.net.ssl.keyStorePassword", "123456");

        // System.setProperty("javax.net.ssl.trustStore", "./cfg/clienttrust.jks");
        // System.setProperty("javax.net.ssl.trustStorePassword", "123456");

        final SSLServerSocketFactory serverSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try (SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket(serverPort)) {
            // Require client authentication
            serverSocket.setNeedClientAuth(true);
            while (true) {
                SSLSocket socket = (SSLSocket) serverSocket.accept();
                Acceptor acceptor = new Acceptor(socket);
                acceptor.service();
            }
        }
    }
}


/**

 export M2_HOME=/opt/maven
 export MAVEN_HOME=/opt/maven
 export PATH=${M2_HOME}/bin:${PATH}

 mvn exec:java

 **/
