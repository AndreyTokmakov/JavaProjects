package ssh;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;

public class SSHClientTests
{
    private static final String HOST_1 = "192.168.101.1";
    private static final String HOST_2 = "192.168.101.2";
    private static final String USER_NAME = "andtokm";
    private static final String ROOT_USER = "root";
    private static final String PASSWORD = "123!@#QWEqwe";

    static void execRemoteCommand(String username, String password,
                                  String host, int port, String command) throws Exception {

        Session session = null;
        ChannelExec channel = null;

        try {
            session = new JSch().getSession(username, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
            channel.setOutputStream(responseStream);
            channel.connect();

            while (channel.isConnected()) {
                Thread.sleep(100);
            }

            String responseString = new String(responseStream.toByteArray());
            System.out.println(responseString);
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    static void keyBasedAuthorization(String username, String host, int port)
    {
        try {
            final JSch jsch = new JSch();
            final String privateKey = "/home/andtokm/DiskS/Jenkins/config/agent_keys/id_rsa";

            jsch.addIdentity(privateKey, "12345");
            // System.out.println("identity added ");

            final Session session = jsch.getSession(username, host, port);
            System.out.println("session created.");

            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            System.out.println("session connected.....");

            /*
            Channel channel = session.openChannel("sftp");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect();

            System.out.println("shell channel connected....");

            ChannelSftp c = (ChannelSftp) channel;

            String fileName = "test.txt";
            c.put(fileName, "./in/");
            c.exit();
            */

            System.out.println("done");

        } catch (final Exception exc) {
            System.err.println(exc);
        }
    }


    public static void main(String[] args) throws Exception
    {
        // execRemoteCommand(USER_NAME, PASSWORD, HOST_1, 22, "duf");
        // execRemoteCommand(USER_NAME, PASSWORD, HOST_2, 22, "duf");

        // execRemoteCommand(ROOT_USER, PASSWORD, HOST_2, 22, "duf");
        execRemoteCommand(ROOT_USER, PASSWORD, HOST_2, 22, "pwd");

        // keyBasedAuthorization(USER_NAME, HOST,22);
    }
}
