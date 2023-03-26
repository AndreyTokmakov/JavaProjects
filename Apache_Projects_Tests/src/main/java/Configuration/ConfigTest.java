package Configuration;

import org.apache.commons.configuration2.*;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.awt.*;

public class ConfigTest {
    private final static String PROP_FILE_NAME = "usergui.properties";

    public static void main(String[] args)
    {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(PROP_FILE_NAME));
        try {
            Configuration config = builder.getConfiguration();

            String backColor = config.getString("colors.background");
            Dimension size = new Dimension(config.getInt("window.width"), config.getInt("window.height"));

            System.out.println("backColor = " + backColor);
            System.out.println("size = " + size);

        } catch (final ConfigurationException exc) {
            System.err.println(exc);
        }
    }
}
