package Configuration;

import org.apache.commons.configuration2.*;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.awt.*;

public class DelimiterTests {
    private final static String PROP_FILE_NAME = "chart.colors.properties";

    public static void main(String[] args)
    {
        Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(PROP_FILE_NAME)
                                .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
        try {
            Configuration config = builder.getConfiguration();

            String[] colors = config.getStringArray("colors.pie");


        } catch (final ConfigurationException exc) {
            System.err.println(exc);
        }
    }
}
