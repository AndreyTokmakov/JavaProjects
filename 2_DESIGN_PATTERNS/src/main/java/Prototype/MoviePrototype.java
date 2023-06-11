package Prototype;


interface IPrototype extends Cloneable {
    IPrototype clone() throws CloneNotSupportedException;
}

class Movie implements IPrototype
{
    private String name;

    public String getName() {
        return name;
    }

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

class Album implements IPrototype
{
    private String name;

    public String getName() {
        return name;
    }

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

class Show implements IPrototype
{
    private String name = null;

    public String getName() {
        return name;
    }

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
    public static class ModelType
    {
        public static final String MOVIE = "movie";
        public static final String ALBUM = "album";
        public static final String SHOW = "show";
    }
    private static final java.util.Map<String , IPrototype> prototypes =
            new java.util.HashMap<String , IPrototype>();

    static
    {
        prototypes.put(ModelType.MOVIE, new Movie());
        prototypes.put(ModelType.ALBUM, new Album());
        prototypes.put(ModelType.SHOW, new Show());
    }

    public static IPrototype getInstance(final String s) throws CloneNotSupportedException {
        return ((IPrototype) prototypes.get(s)).clone();
    }
}

public class MoviePrototype
{
    public static void main(String[] args)
    {
        try
        {
            String moviePrototype = PrototypeFactory.getInstance(PrototypeFactory.ModelType.MOVIE).toString();
            System.out.println(moviePrototype);

            String albumPrototype = PrototypeFactory.getInstance(PrototypeFactory.ModelType.ALBUM).toString();
            System.out.println(albumPrototype);

            String showPrototype = PrototypeFactory.getInstance(PrototypeFactory.ModelType.SHOW).toString();
            System.out.println(showPrototype);

        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
    }
}
