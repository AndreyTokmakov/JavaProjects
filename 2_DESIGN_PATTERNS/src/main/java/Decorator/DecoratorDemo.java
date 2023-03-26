package Decorator;

interface Widget {
    void draw();
}

class TextField implements Widget {
    private final int width;
    private final int height;

    public TextField(int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void draw() {
        System.out.println("TextField: " + width + ", " + height);
    }
}

abstract class WidgetDecorator implements Widget {
    private final Widget widget;

    public WidgetDecorator(Widget widget) {
        this.widget = widget;
    }

    public void draw() {
        widget.draw();
    }
}

class BorderDecorator extends WidgetDecorator {
    public BorderDecorator(Widget widget) {
        super(widget);
    }
    public void draw() {
        super.draw();
        System.out.println("  BorderDecorator");
    }
}

class ScrollDecorator extends WidgetDecorator {
    public ScrollDecorator(Widget widget) {
        super(widget);
    }
    public void draw() {
        super.draw();
        System.out.println("  ScrollDecorator");
    }
}

public class DecoratorDemo
{
    public static void main(String[] args)
    {
        Widget widget = new BorderDecorator(new BorderDecorator(
                new ScrollDecorator(new TextField(80, 24))));
        widget.draw();
    }
}