package Documentation;

/**
 * Product class with <b> maker </b> and <b> price </b> properties.
 * @autor Andrey Tokmakov
 * @version 2.1
*/
public class Product
{
    /** Producer field **/
    private String maker;

    /** Price field */
    public double price;

    /**
     * Constructor - creating a new object
     * @see Product # Product (String, double)
     */
    Product()
    {
        setMaker("");
        price=0;
    }

    /**
     * Constructor - creating a new object with specific values
     * @param maker - producer
     * @param price - price
     * @see Product # Product ()
     */
    Product(String maker,double price){
        this.setMaker(maker);
        this.price=price;
    }

    /**
     * Function for getting the value of the {@link Product # maker} field
     * @return returns the manufacturer's name
     */
    public String getMaker() {
        return maker;
    }

    /**
      * Procedure for determining the manufacturer {@link Product # maker}
      * @param maker - producer
      */
    public void setMaker(String maker) {
        this.maker = maker;
    }
}