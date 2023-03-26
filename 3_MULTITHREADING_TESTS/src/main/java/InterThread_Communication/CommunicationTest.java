/****************************************************************************
* Copyright 2021 (C) Andrey Tokmakov
* CommunicationTest.java class
*
* @name    : CommunicationTest.java
* @author  : Tokmakov Andrey
* @version : 1.0
* @since   : Jan 9, 2021
****************************************************************************/

package InterThread_Communication;

import java.io.PipedReader;
import java.io.PipedWriter;

public class CommunicationTest {
	
    public static void main(String[] args) {
       Test();
    }
 
    protected static void Test() 
    {
        try {
            PipedReader pr = new PipedReader();
            PipedWriter pw = new PipedWriter();
 
            pw.connect(pr);
 
            Thread thread1 = new Thread(new PipeReaderThread("ReaderThread", pr));
            Thread thread2 = new Thread(new PipeWriterThread("WriterThread", pw));
 
            // start both threads
            thread1.start();
            thread2.start();
        } catch (Exception e)  {
            System.out.println("PipeThread Exception: " + e);
        }
    }
}
