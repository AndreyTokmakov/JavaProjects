package wifi;

import com.jcraft.jsch.*;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class SSHClientRemote
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

    // TODO: Use SSH connection pool
    public String execCommand(String command) throws InterruptedException {
        try (final SSHSession session = openSession(username, password, host);
             final SSHExecChannel channel = openChannel(session))
        {
            channel.execCommand(command);

            final int code = channel.getExitStatus();
            if (0 != code) {
                // throw new RuntimeException(String.format("Failed to execute '%s' command. Error: %s", command, errStream));
                System.out.println("CODE = " + code);
            }
            return outStream.toString();

        } catch (final JSchException exc) {
            throw new RuntimeException(exc);
        }
    }

    // TODO: Refactor ????
    public Boolean CheckIsConnectedToAccessPoint(String interfaceName,
                                                 String apName) {
        try {
            String result = execCommand("iw " + interfaceName + " link");
            return Arrays.stream(result.split("\n")).filter(e -> e.contains("SSID") && e.contains(apName)).
                    map(String::trim).findFirst().isPresent();
        } catch (Exception exc) {
            // exc.printStackTrace(); // TODO: Handle exception
            return false;
        }
    }

    // TODO: Refactor
    public Boolean ConnectToWiFiAccessPoint(String apName,
                                            String password) {
        try {
            String result = execCommand("nmcli d wifi connect " + apName + " password " + password);
            // System.out.println(result);
            return true;
        } catch (Exception exc) {
            exc.printStackTrace(); // TODO: Handle exception
            return false;
        }
    }

    public Boolean isClientConnected(String clientMacAddress,
                                     String interfaceName) {
        final String pattern = String.format("Station %s (on %s)", clientMacAddress, interfaceName);
        try {
            final String output = execCommand("iw dev " + interfaceName + " station dump");
            return !output.isEmpty() && output.contains(pattern);
        } catch (Exception exc) {
            // exc.printStackTrace(); // TODO: Handle exception
            return false;
        }
    }
}
