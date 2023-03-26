/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* PipeReaderThread.java class
*
* @name    : PipeReaderThread.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 9, 2021
****************************************************************************/

package InterThread_Communication;

import java.io.PipedReader;

public class PipeReaderThread implements Runnable {
    private PipedReader pipedReader;
    private String name = null;
 
    public PipeReaderThread(String name, PipedReader pr) {
        this.name = name;
        this.pipedReader = pr;
    }
 
    public void run()  {
        try {  // continuously read data from stream and print it in console
            while (true) {
                char c = (char) pipedReader.read(); // read a char
                if (c != -1) { // check for -1 indicating end of file
                    System.out.print(c);
                }
            }
        } catch (Exception e) {
            System.out.println(" PipeThread Exception: " + e);
        }
    }
}