package ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

class Credentials {
    public static final String HOST_T14_FIRST = "192.168.101.1";
    public static final String HOST_T14_SECOND = "192.168.101.2";
    public static final String USER_NAME = "andtokm";
    public static final String ROOT_USER = "root";
    public static final String PASSWORD = "123!@#QWEqwe";

    public static final String CSL_USER = "root";
    public static final String CSL_PASSWORD = "root";
    public static final String CSL_HOST = "192.168.101.101";

    private Credentials() {
    }
}

public class SSHClient
{
    private final JSch jsch = new JSch();
    private static final int SSH_PORT_DEFAULT = 22;
    private final String username;
    private final String password;
    private final String host;
    final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    final ByteArrayOutputStream errStream = new ByteArrayOutputStream();

    public SSHClient(String username, String password, String host) {
        this.username = username;
        this.password = password;
        this.host = host;
    }

    private Session openSession(String username, String password, String host) throws JSchException {
        return openSession(username, password, host, SSH_PORT_DEFAULT);
    }

    private Session openSession(String username, String password, String host, int port) throws JSchException {
        final Session session = new JSch().getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no"); // TODO: To consts
        session.connect();
        return session;
    }

    public Boolean execCommand(String command) throws InterruptedException {
        Session session = null;
        ChannelExec channel = null;
        try {
            session = openSession(username, password, host);

            channel = (ChannelExec) session.openChannel("exec"); // TODO: --> conts
            channel.setCommand(command);
            channel.setOutputStream(outStream);
            channel.setErrStream(errStream);
            channel.connect();

            while (channel.isConnected()) {
                TimeUnit.MILLISECONDS.sleep(100); // TODO: 100 --> contsts
            }

            String output = "";
            if (0 == channel.getExitStatus()) {
                output = outStream.toString();
            } else {
                // TODO: Report error
                output = errStream.toString();
            }
            System.out.println(output);

            errStream.reset();
            outStream.reset();

        } catch (final JSchException exc) {
            // throw new RuntimeException(e);
            System.err.println(exc);
            return false;
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }

        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        SSHClient client = new SSHClient(Credentials.ROOT_USER, Credentials.PASSWORD, Credentials.HOST_T14_SECOND);
        // client.execCommand("duf");
        client.execCommand("pwd");
    }
}
