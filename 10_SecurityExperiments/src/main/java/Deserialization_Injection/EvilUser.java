package Deserialization_Injection;


import java.io.Serial;

public class EvilUser extends User
{
    public EvilUser(String name) {
        super(name);
    }

    // @Serial
    private void readObject(java.io.ObjectInputStream in)
    {
        // Malicious action: Delete a file
        try {
            System.out.println("*** HACK ****");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}