package ResourceBundle;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

class Bundle_Test_1 {
	
	private final static String currPath = "R:\\Projects\\Java\\JavaStudyProject\\src\\ResourceBundle";
	
	private ClassLoader getLocalClassLoader() {
		File file = new File(currPath);
    	try {
    		URL[] urls = { file.toURI().toURL() };
    		return new URLClassLoader(urls);
    	} catch (final MalformedURLException exc){
    		System.err.println(exc);
		}
    	return null;
	}
	
    private void printInfo(final String language, 
    					   final String country) throws UnsupportedEncodingException {
        Locale current = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle("text", current);
        String s1 = rb.getString("str1");

        System.out.println(s1);
        String s2 = rb.getString("str2");
        System.out.println(s2);
        System.out.println();
    }
    
    public void PrintInfoTests() throws UnsupportedEncodingException {
    	this.printInfo("", "");
    	this.printInfo("en", "US");
    	this.printInfo("uk", "UA");
    }
    
    public void CreateBundle_1() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();

        // create a new ResourceBundle.Control with default format
        Control rbc = Control.getControl(Control.FORMAT_DEFAULT);

        // create a new ResourceBundle with specified locale and SystemClassLoader and a Control
        ResourceBundle bundle = ResourceBundle.getBundle("hello", Locale.US, cl, rbc);

        // print the text assigned to key "hello"
        System.out.println("" + bundle.getString("hello"));
     }

    public void GetBundle() {
    	ClassLoader loader = this.getLocalClassLoader();
    	
    	System.out.println("Current Locale: " + Locale.getDefault());
    	ResourceBundle mybundle = ResourceBundle.getBundle("MyLabels", Locale.getDefault(), loader);

    	// read MyLabels_en_US.properties
    	System.out.println("Say how are you in US English: " + mybundle.getString("how_are_you"));

    	// Set default bandle
    	Locale.setDefault(new Locale("ms", "MY"));

    	// read MyLabels_ms_MY.properties
    	System.out.println("Current Locale: " + Locale.getDefault());
    	mybundle = ResourceBundle.getBundle("MyLabels", Locale.getDefault(), loader);
    	System.out.println("Say how are you in Malaysian Malaya language: " + mybundle.getString("how_are_you"));
     }
    
    public void ReadBundle_Simple() {
    	ClassLoader loader = this.getLocalClassLoader();
    	Locale locale = Locale.ENGLISH;
        ResourceBundle myResources = ResourceBundle.getBundle("TestData",locale, loader);
         
        System.out.println("HelpKey: " + myResources.getString("HelpKey"));
        System.out.println("CancelKey: " + myResources.getString("CancelKey"));
        System.out.println("NoKey: " + myResources.getString("NoKey"));
    }
    
}

public class ResourceBundleTests {
	public static void main(String[] args) throws UnsupportedEncodingException, MalformedURLException {
		Bundle_Test_1 tests = new Bundle_Test_1();
		
		//tests.GetBundle();
		tests.ReadBundle_Simple();
	}
}
