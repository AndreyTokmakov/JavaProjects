package Documentation;

/**
 * TestClass class with <b> maker </b> and <b> price </b> properties.
 * @autor Andrey Tokmakov
 * @version 0.1
*/
public class TestClass {
	
    /** name field **/
    private String name;

    /** Price field */
    public double price;

    /**
     * Constructor - creating a new object
     * @see Product # Product (String, double)
     */
    TestClass()
    {
        price=0;
    }

    /**
     * Constructor - creating a new object with specific values
     * @param maker - producer
     * @param price - price
     * @see Product # Product ()
     */
    TestClass(String maker,double price){;
        this.price=price;
    }

}
