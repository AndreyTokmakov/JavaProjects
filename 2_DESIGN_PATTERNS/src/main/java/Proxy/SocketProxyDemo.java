//============================================================================
// Name        : SocketProxyDemo.java
// Created on  : August 30, 2020
// Author      : Tokmakov Andrey
// Version     : 0.1
// Copyright   : Your copyright notice
// Description : Proxy design pattern demo
//============================================================================

package Proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

// To support plug-compatibility between
// the wrapper and the target, create an interface
interface SocketInterface {
    String readLine();
    void writeLine(String str);
    void dispose();
}

// "wrapper" for a remote, or expensive, or sensitive target
class SocketProxy implements SocketInterface {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public SocketProxy(String host, int port, boolean wait) {
        try {
            if (wait) {
                // Encapsulate the complexity/overhead of the target in the wrapper
                ServerSocket server = new ServerSocket(port);
                socket = server.accept();
            } else {
                socket = new Socket(host, port);
            }
            in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String readLine() {
        String str = null;
        try {
            str = in.readLine();
        } catch( IOException e ) {
            e.printStackTrace();
        }
        return str;
    }

    // The wrapper delegates to the target
    @Override
    public void writeLine(String str) {
        out.println(str);
    }

    @Override
    public void dispose() {
        try {
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

public class SocketProxyDemo {
	public static void main(String[] args) {
        // The client deals with the wrapper
        SocketInterface socket = new SocketProxy( "127.0.0.1", 8080, args[0].equals("first") ? true : false );
        
        String  str;
        boolean skip = true;
        while (true) {
            if (args[0].equals("second") && skip) {
                skip = !skip;
            } else {
                str = socket.readLine();
                System.out.println("Receive - " + str);
                if (str.equals(null)) {
                    break;
                }
            }
            System.out.print( "Send ---- " );
            str = new Scanner(System.in).nextLine();
            socket.writeLine( str );
            if (str.equals("quit")) {
                break;
            }
        }
        socket.dispose();
	}
}
