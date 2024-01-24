package Prototype;


import lombok.Getter;

import java.util.EnumMap;

interface IPrototype extends Cloneable {
    IPrototype clone() throws CloneNotSupportedException;
}

@Getter
class Movie implements IPrototype
{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Movie clone() throws CloneNotSupportedException {
        System.out.printf("Cloning %s object..%n", this.getClass().getSimpleName());
        return (Movie) super.clone();
    }

    @Override
    public String toString() {
        return "Movie";
    }
}

@Getter
class Album implements IPrototype
{
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Album clone() throws CloneNotSupportedException {
        System.out.printf("Cloning %s object..%n", this.getClass().getSimpleName());
        return (Album) super.clone();
    }

    @Override
    public String toString() {
        return "Album";
    }
}

@Getter
class Show implements IPrototype
{
    private String name = null;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Show clone() throws CloneNotSupportedException {
        System.out.printf("Cloning %s object..%n", this.getClass().getSimpleName());
        return (Show) super.clone();
    }

    @Override
    public String toString() {
        return "Show";
    }
}

class PrototypeFactory
{

    public enum ModelType {
        Movie,
        Album,
        Show
    }

    private static final EnumMap<ModelType, IPrototype> prototypes =
            new EnumMap<ModelType , IPrototype>(ModelType.class);

    static
    {
        prototypes.put(ModelType.Movie, new Movie());
        prototypes.put(ModelType.Album, new Album());
        prototypes.put(ModelType.Show, new Show());
    }

    public static IPrototype getInstance(ModelType type) throws CloneNotSupportedException {
        return ((IPrototype) prototypes.get(type)).clone();
    }
}

public class MoviePrototype
{
    public static void main(String[] args)
    {
        try
        {
            String moviePrototype = PrototypeFactory.getInstance(PrototypeFactory.ModelType.Movie).toString();
            System.out.println(moviePrototype);

            String albumPrototype = PrototypeFactory.getInstance(PrototypeFactory.ModelType.Album).toString();
            System.out.println(albumPrototype);

            String showPrototype = PrototypeFactory.getInstance(PrototypeFactory.ModelType.Show).toString();
            System.out.println(showPrototype);

        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
    }
}
