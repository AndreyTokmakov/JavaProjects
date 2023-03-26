/****************************************************************************
* Copyright 2020 (C) Andrey Tokmakov
* 
* Template Method is a behavioral design pattern that allows you to defines a
* skeleton of an algorithm in a base class and let subclasses override the
* steps without changing the overall algorithms structure.
*
* @name      : TemplateMethod_Demo1.java
* @author    : Tokmakov Andrey
* @version   : 1.0
* @since     : October 22, 2020
****************************************************************************/ 

package TemplateMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class of social network.
 */
abstract class Network {
	protected String userName;
	protected String password;

    public Network() {}

    /**
     * Publish the data to whatever network.
     */
    public boolean post(String message) {
        // Authenticate before posting. Every network uses a different authentication method.
        if (login(this.userName, this.password)) {
            // Send the post data.
            boolean result =  sendData(message.getBytes());
            logout();
            return result;
        }
        return false;
    }

    public abstract boolean login(String userName, String password);
    public abstract void logout();
    
    public abstract boolean sendData(byte[] data);
}

/**
 * Class of social network
 */
class Facebook extends Network {
	
    public Facebook(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean login(String userName, String password) {
        System.out.println("\nChecking user's parameters");
        System.out.println("Name: " + this.userName);
        System.out.print("Password: ");
        for (int i = 0; i < this.password.length(); i++) {
            System.out.print("*");
        }
        simulateNetworkLatency();
        System.out.println("\n\nLogIn success on Facebook");
        return true;
    }

    @Override
    public void logout() {
        System.out.println("User: '" + userName + "' was logged out from Facebook");
    }
    
    public boolean sendData(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Facebook");
            return true;
        } else {
            return false;
        }
    }

    private void simulateNetworkLatency() {
        try {
            int i = 0;
            System.out.println();
            while (i < 3) {
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}


/**
 * Class of social network
 */
class Twitter extends Network {

    public Twitter(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public boolean login(String userName, String password) {
        System.out.println("\nChecking user's parameters");
        System.out.println("Name: " + this.userName);
        System.out.print("Password: ");
        for (int i = 0; i < this.password.length(); i++) {
            System.out.print("*");
        }
        simulateNetworkLatency();
        System.out.println("\n\nLogIn success on Twitter");
        return true;
    }

    @Override
    public void logout() {
        System.out.println("User: '" + userName + "' was logged out from Twitter");
    }
    
    public boolean sendData(byte[] data) {
        boolean messagePosted = true;
        if (messagePosted) {
            System.out.println("Message: '" + new String(data) + "' was posted on Twitter");
            return true;
        } else {
            return false;
        }
    }

    private void simulateNetworkLatency() {
        try {
            int i = 0;
            System.out.println();
            while (i < 3) {
                System.out.print(".");
                Thread.sleep(500);
                i++;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

public class TemplateMethod_Demo1 {
	public static void main(String[] args) {
		String userName = "Jhonatan", password = "12345", message  = "Hello, World!";

		{
			Network network = new Facebook(userName, password);
			network.post(message);
		}
		{
			Network network = new Twitter(userName, password);
			network.post(message);
		}
	}
}
