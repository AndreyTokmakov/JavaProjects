package tcp_clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient
{
    /** **/
    private BufferedReader bufferedReader = null;

    /** **/
    private Socket socket = null;
    /** **/
    private OutputStream outputStream = null;
    /** **/
    private BufferedReader socketReader = null;
    /** **/
    private boolean connected = false;
    /** **/
    private boolean run = true;

    /** **/
    public TCPClient() {
        // TODO Auto-generated constructor stub
    }


    public void Run() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (run) {
                System.out.print("> ");
                String command = bufferedReader.readLine();
                String response = HandleCommand(command);
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /** Handle command : **/
    protected String HandleCommand(String command)  {
        String result = "";
        if (!connected && command.contains("connect ") && command.contains(":"))
            result = HandleConnect(command);
        else if (connected && command.contains("disconnect"))
            result = HandleDisconnect(command);
        else if (!connected && command.contains("quit"))
            result = HandleQuit(command);
        else if (connected && !command.contains("disconnect"))
            result = HandleCommandToServer(command);
        else
            result = "Handle command error : " + command;
        return result;
    }

    /** Handle connect command : **/
    protected String HandleCommandToServer(String command)  {
        String result = "";
        int c = 0;
        String cmd = command + "\r\n";
        try {
            outputStream.write(cmd.getBytes("iso8859_1"));
            String response = socketReader.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /** Handle connect command : **/
    protected String HandleConnect(String command)  {
        String result = "";

        int pos = command.indexOf("connect ");
        String ipPortStr = command.substring(pos + 8, command.length());
        String[] tmp = ipPortStr.split(":");

        String ip = tmp[0];
        int port  = Integer.parseInt(tmp[1]);

        System.out.println("Connecting to " + ip + ":" + port + "...");

        this.connected = this.Connect(ip, port);
        return result;
    }

    /** Handle disconnect command : **/
    protected String HandleDisconnect(String command)  {
        String result = "";
        if (this.Disconnect())
            this.connected = false;
        return result;
    }

    /** Handle quit command : **/
    protected String HandleQuit(String command)  {
        String result = "Good bye";
        this.run = false;
        return result;
    }

    /** Connect : **/
    private boolean Connect(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.outputStream = socket.getOutputStream();
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** Disconnect : **/
    private boolean Disconnect() {
        if (this.socket.isConnected()) {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    /** **/
    public static void main(String[] args) {
        TCPClient client = new TCPClient();
        client.Run();
    }
}
