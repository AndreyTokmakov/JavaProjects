/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* PipeWriterThread.java class
*
* @name    : PipeWriterThread.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 9, 2021
****************************************************************************/

package InterThread_Communication;

import java.io.PipedWriter;

public class PipeWriterThread implements Runnable {
    private PipedWriter pipedWriter;
    private String name = null;
 
    public PipeWriterThread(String name, PipedWriter pw) {
        this.name = name;
        this.pipedWriter = pw;
    }
 
    public void run() {
        try {
            while (true) {
                System.out.println(this.name + " writing message.");
            	pipedWriter.write("Testing data written...\n");
            	pipedWriter.flush();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            System.out.println(" PipeThread Exception: " + e);
        }
    }
}