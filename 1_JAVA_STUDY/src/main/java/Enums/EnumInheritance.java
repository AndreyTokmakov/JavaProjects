package Enums;

public class EnumInheritance
{
    public interface Annotation {
        public String getValue();
    }

    enum JUnitAnnotation implements Annotation {
        Test ("Test"),
        After ("After"),
        AfterClass ("AfterClass"),
        Before ("Before"),
        BeforeClass ("BeforeClass"),
        Ignores ("Ignores");


        @Override
        public String getValue() {
            return value;
        }

        JUnitAnnotation(String value) {
            this.value = value;
        }

        final String value;
    }

    public static void HandleAnnotation(Annotation annotation) {
        System.out.println(annotation.getValue());
    }

    public static void main(String[] args)
    {
        HandleAnnotation(JUnitAnnotation.Test);
        HandleAnnotation(JUnitAnnotation.Before);
        HandleAnnotation(JUnitAnnotation.After);
    }
}
