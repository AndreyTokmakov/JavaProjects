
import org.apache.commons.jcs3.*;
import org.apache.commons.jcs3.access.CacheAccess;
import org.apache.commons.jcs3.access.exception.CacheException;

public class JcsExample {
	/** **/
    private CacheAccess<String, City> cache = null;

    public JcsExample()  {
        try  {
            cache = JCS.getInstance("default");
        } catch (CacheException exc) {
            System.out.println(String.format("Problem initializing cache: %s", exc.getMessage()));
        }
    }

    public boolean putInCache(City city)   {
        String key = city.getName();
        try {
            cache.put(key, city);
        } catch (CacheException e) {
            System.out.println(String.format("Problem putting city %s in the cache, for key %s%n%s", key, key, e.getMessage()));
            return false;
        }
        return true;
    }

    public City retrieveFromCache(String cityKey)  {
        return cache.get(cityKey);
    }

    public void test() {
        putInCache(new City("Zurich",       "Switzerland",  366765));
        putInCache(new City("Berlin",       "Germany",      3502000));
        putInCache(new City("Johannesburg", "South Africa", 12200000));
        
        String[] names = new String[] {"Berlin","New York"};
        for (String cityName : names) {
        	City city = retrieveFromCache(cityName); 
            if (city != null )  {
                System.out.println(city.toString() );
            } else {
                System.out.println("No object was found in the cache for the key \"" + cityName + "\"");
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////
    //                                          MAIN                                               //
    /////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main( String[] args ) {
        new JcsExample().test();
    }
}