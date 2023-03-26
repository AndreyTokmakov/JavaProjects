package Serialization;

import java.io.*;
import java.util.List;

public class SerializeLists
{
    private final static String FILE_NAME = "S:\\Temp\\TESTING_ROOT_DIR\\animals.dat";

    protected static void PutObject() throws IOException
    {
        try (FileOutputStream fileStream = new FileOutputStream(FILE_NAME);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileStream)) {
            try {
                final List<Animal> list = List.of(new Animal("Cat"), new Animal("Dog"));
                // Animal params = new Animal(name);
                objectOutputStream.writeObject(list);
            } catch (final IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    protected static void GetObject() throws IOException {
        try (FileInputStream fileStream = new FileInputStream(FILE_NAME);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileStream)) {
            try {
                // Animal data = (Animal)objectInputStream.readObject();
                final List<Animal> list = (List<Animal>)objectInputStream.readObject();
                list.forEach(System.out::println);
            } catch (final ClassNotFoundException | IOException exc) {
                exc.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // PutObject();
        GetObject();
    }
}
