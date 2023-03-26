package wifi;

import com.jcraft.jsch.*;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

class Credentials {
    public static final String HOST_1 = "192.168.101.1";
    public static final String HOST_2 = "192.168.101.2";
    public static final String USER_NAME = "andtokm";
    public static final String ROOT_USER = "root";
    public static final String PASSWORD = "123!@#QWEqwe";

    public static final String CSL_USER = "root";
    public static final String CSL_PASSWORD = "root";
    public static final String CSL_HOST = "192.168.101.100";

    private Credentials() {
    }
}

class SSHClientRemote
{
    private final JSch jsch = new JSch();
    private static final int SSH_PORT_DEFAULT = 22;
    private final String username;
    private final String password;
    private final String host;
    final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    final ByteArrayOutputStream errStream = new ByteArrayOutputStream();

    /** SSHSession record: jsch.Session class wrapper. **/
    private static final record SSHSession(Session session) implements AutoCloseable {
        @Override
        public void close() {
            session.disconnect();
        }

        public Channel openChannel(String type) throws JSchException {
            return session.openChannel(type);
        }

        public ChannelExec openExecChannel() throws JSchException {
            return (ChannelExec)openChannel("exec");  // TODO: --> const
        }
    }

    /** SSHExecChannel record: jsch.ChannelExec class wrapper. **/
    private static final class SSHExecChannel implements AutoCloseable {
        final ChannelExec channel;
        final ByteArrayOutputStream outputStream;
        final ByteArrayOutputStream errorStream;

        public SSHExecChannel(SSHSession channel,
                              ByteArrayOutputStream outputStream,
                              ByteArrayOutputStream errorStream) throws JSchException {
            this.outputStream = outputStream;
            this.errorStream = errorStream;
            this.channel = channel.openExecChannel();

            this.channel.setErrStream(this.errorStream);
            this.channel.setOutputStream(this.outputStream);
        }

        public void execCommand(String command) throws JSchException, InterruptedException {
            channel.setCommand(command);
            channel.connect(); // TODO: Set timeout??

            while (channel.isConnected()) {
                TimeUnit.MILLISECONDS.sleep(10); // TODO: 10 --> const
            }
        }

        public int getExitStatus()  {
            return channel.getExitStatus();
        }

        @Override
        public void close() {
            outputStream.reset();
            errorStream.reset();
            channel.disconnect();
        }
    }

    public SSHClientRemote(String username, String password, String host) {
        this.username = username;
        this.password = password;
        this.host = host;
    }

    private SSHSession openSession(String username, String password, String host) throws JSchException {
        return openSession(username, password, host, SSH_PORT_DEFAULT);
    }

    private SSHSession openSession(String username, String password, String host, int port) throws JSchException {
        final Session session = new JSch().getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no"); // TODO: To const
        session.connect();
        return new SSHSession(session);
    }

    private SSHExecChannel openChannel(SSHSession session) throws JSchException {
        return new SSHExecChannel(session, outStream, errStream);
    }

    public String execCommand(String command) throws InterruptedException {
        try (final SSHSession session = openSession(username, password, host);
             final SSHExecChannel channel = openChannel(session))
        {
            channel.execCommand(command);
            if (0 != channel.getExitStatus())
                throw new RuntimeException(String.format("Failed to execute '%s' command. Error: %s", command, errStream));
            return outStream.toString();

        } catch (final JSchException exc) {
            throw new RuntimeException(exc);
        }
    }
}


public class WiFiManagerRemote
{
    public static void main(String[] args) throws InterruptedException
    {
        // SSHClientRemote client = new SSHClientRemote(Credentials.ROOT_USER, Credentials.PASSWORD, Credentials.HOST_2);
        SSHClientRemote client = new SSHClientRemote(Credentials.CSL_USER, Credentials.CSL_USER, Credentials.CSL_HOST);

        // String output = client.execCommand("df -h");
        // String output = client.execCommand("ps axf");
        String output = client.execCommand("iw dev wlan1 station dump | grep Station");
        // String output = client.execCommand("ps axf | grep ssh");
        // String output = client.execCommand("nmcli d wifi list");

        System.out.println(output);
    }
}
