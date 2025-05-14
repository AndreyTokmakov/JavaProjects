package ObjectOrientedProgramming.Static_Initialization_Block;

import javax.swing.*;

public class HandleException
{
    public static final class Resource
    {
        public Resource()
        {
            throw new RuntimeException("Failed to construct Resource");
        }
    }

    public static final class Worker
    {
        static Resource resource;

        static {
            resource = new Resource();
        }
    }

    public static void main(String[] args)
    {
        Worker worker = new Worker();
    }
}
