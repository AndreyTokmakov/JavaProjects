package examples;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Value
class ImmutableProduct
{
    String name;
    double price;
}

@Data
@AllArgsConstructor
class Product
{
    String name;
    double price;
}

public class Value_Test
{
    public static void main(String[] args)
    {
        {
            Product product = new Product("Milk", 4.5);
            System.out.println(product);
            product.setPrice(4.1232323);

            System.out.println(product);
        }

        {
            ImmutableProduct product = new ImmutableProduct("Milk", 4.5);
            System.out.println(product);

            // product.setPrice(4.1232323);  // Will Not compile

            System.out.println(product);
        }
    }
}
