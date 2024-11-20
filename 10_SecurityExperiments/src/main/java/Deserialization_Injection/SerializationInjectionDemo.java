package Deserialization_Injection;

import java.io.*;
import java.nio.file.Path;
import java.util.Optional;


public class SerializationInjectionDemo
{
    public Optional<User> DeserializeUser(ObjectInputStream inputStream) throws IOException
    {
        try {
            final User user = (User)inputStream.readObject();
            return Optional.of(user);
        } catch (final ClassNotFoundException | IOException exc) {
            System.err.println(exc.getMessage());
        }
        return Optional.empty();
    }

    public Optional<User> DeserializeUser_FromFile(Path filePath)
    {
        try (final FileInputStream inFileStream = new FileInputStream(filePath.toString()))
        {
            return DeserializeUser(new ObjectInputStream(inFileStream));
        } catch(final IOException exc) {
            System.err.println(exc.getMessage());
        }
        return Optional.empty();
    }

    public void SerializeUser_ToFile(User user, Path filePath)
    {
        try (final FileOutputStream fileStream = new FileOutputStream(filePath.toString());
             final ObjectOutputStream outputStream = new ObjectOutputStream(fileStream))
        {
            outputStream.writeObject(user);
        } catch(final IOException exc) {
            System.err.println(exc.getMessage());
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        final SerializationInjectionDemo demo = new SerializationInjectionDemo();
        final User user = new User("John Dow");
        final EvilUser evilUser = new EvilUser("John Dow (Evil)");

        final Path userFilePath = Path.of("/home/andtokm/Temp/Java/user.ser");
        final Path userFilePathEvil = Path.of("/home/andtokm/Temp/Java/evil_user.ser");

        demo.SerializeUser_ToFile(user, userFilePath);
        demo.SerializeUser_ToFile(evilUser, userFilePathEvil);


        Optional<User> restoredUser = demo.DeserializeUser_FromFile(userFilePathEvil);
        restoredUser.ifPresent(System.out::println);
    }
}
