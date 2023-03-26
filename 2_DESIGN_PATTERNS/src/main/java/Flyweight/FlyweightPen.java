package Flyweight;
import java.util.HashMap;

interface Pen
{
    void setColor(String color);
    void draw(String content);
}
enum BrushSize {
    THIN,
    MEDIUM,
    THICK
}

class ThickPen implements Pen {
    final BrushSize brushSize = BrushSize.THICK;
    private String color = null;

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void draw(String content) {
        System.out.println("Drawing THICK content in color : " + color);
    }
}

class ThinPen implements Pen {
    final BrushSize brushSize = BrushSize.THIN;
    private String color = null;

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void draw(String content) {
        System.out.println("Drawing THIN content in color : " + color);
    }
}

class MediumPen implements Pen {
    final BrushSize brushSize = BrushSize.MEDIUM;
    private String color = null;

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public void draw(String content) {
        System.out.println("Drawing MEDIUM content in color : " + color);
    }
}

class PenFactory
{
    private static final HashMap<String, Pen> pensMap = new HashMap<>();

    public static Pen getThickPen(String color)
    {
        String key = color + "-THICK";
        Pen pen = pensMap.get(key);

        if (null == pen) {
            pen = new ThickPen();
            pen.setColor(color);
            pensMap.put(key, pen);
        }

        return pen;
    }

    public static Pen getThinPen(String color)
    {
        final String key = color + "-THIN";
        Pen pen = pensMap.get(key);

        if (null == pen) {
            pen = new ThinPen();
            pen.setColor(color);
            pensMap.put(key, pen);
        }

        return pen;
    }

    public static Pen getMediumPen(String color)
    {
        final String key = color + "-MEDIUM";
        Pen pen = pensMap.get(key);

        if (null == pen) {
            pen = new MediumPen();
            pen.setColor(color);
            pensMap.put(key, pen);
        }

        return pen;
    }
}

public class FlyweightPen
{
    public static void main(String[] args)
    {
        Pen yellowThinPen1 = PenFactory.getThickPen("YELLOW");  // created new pen
        yellowThinPen1.draw("Hello World !!");

        Pen yellowThinPen2 = PenFactory.getThickPen("YELLOW");  // pen is shared
        yellowThinPen2.draw("Hello World !!");

        Pen blueThinPen = PenFactory.getThickPen("BLUE");       // created new pen
        blueThinPen.draw("Hello World !!");

        Pen blueMediumPen = PenFactory.getMediumPen("BLUE");    // created new pen
        blueMediumPen.draw("Hello World !!");

        Pen yellowMediumPen = PenFactory.getMediumPen("BLUE");    // created new pen
        yellowMediumPen.draw("Hello World !!");


        System.out.println(yellowThinPen1.hashCode());
        System.out.println(yellowThinPen2.hashCode());
        System.out.println(blueThinPen.hashCode());
        System.out.println(blueMediumPen.hashCode());
        System.out.println(yellowMediumPen.hashCode());
    }
}
