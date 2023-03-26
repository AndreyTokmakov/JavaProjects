package Serialization;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class SerializeArraysOfObjects
{
    public static void main(String[] args)
    {
        byte[] serializedData = serialize();
        final Animal[] result = deserializeAnimalArray(serializedData);
        for (Animal a: result)
            System.out.println(a);
    }

    public static byte[]  serialize() {
        Animal[] animals = new Animal[] { new Animal("cat"),
                new Animal("dog1"),
                new Animal("duck"),
                new Animal("horse")};

        // System.out.println(Arrays.toString(animals));
        byte[] serializedAnimals = serializeAnimalArray(animals);
        return serializedAnimals;
    }

    public static void deserialize(byte[] data) {
        Animal[] deserializedAnimals = deserializeAnimalArray(data);
        // System.out.println(Arrays.toString(deserializedAnimals));
    }

    public static byte[] serializeAnimalArray(Animal[] animals) {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             final ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeInt(animals.length);
            for (Animal animal : animals)
                oos.writeObject(animal);
            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Animal[] deserializeAnimalArray(byte[] data) {
        try (final ByteArrayInputStream outputStream = new ByteArrayInputStream(data);
             final ObjectInputStream inputStream = new ObjectInputStream(outputStream)) {
            final int recordsCount = inputStream.readInt();
            final Animal[] result = new Animal[recordsCount];
            for (int i = 0; i < recordsCount; ++i)
                result[i] = (Animal)inputStream.readObject();
            return result;
        } catch (IOException | java.lang.ClassCastException | ClassNotFoundException e) {
            throw new java.lang.IllegalArgumentException(e);
        }
    }
}
