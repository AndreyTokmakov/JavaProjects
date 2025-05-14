package Record;

public class DefaultValues
{
    public record AccountConfig(String key, String secret) {

    }

    public static void main(String[] args) {
        AccountConfig config = new AccountConfig("1", "232");
        System.out.println(config);


        System.out.println(config);
    }
}
