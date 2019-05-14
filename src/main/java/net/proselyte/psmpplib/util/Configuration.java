package net.proselyte.psmpplib.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration class that takes property from smpp.properties file by default.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public class Configuration {
    static Properties configurationProperties = new Properties();
    private static final String[] requiredProperties = new String[]{"smppHost", "smppPort", "smppSystemId", "smppPassword"};

    public Configuration() {
        try {
            InputStream input = new FileInputStream("src/main/resources/smpp.properties");
            configurationProperties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Validate properties file
        for (String requiredProperty : requiredProperties) {
            if (!configurationProperties.containsKey(requiredProperty)) {
                throw new IllegalArgumentException("PSMPPLib: Configuration file doesn't contain required field: " + requiredProperty);
            }
        }
    }

    public Configuration(String propertyFileLocation) {
        try {
            InputStream input = new FileInputStream(propertyFileLocation);
            configurationProperties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String requiredProperty : requiredProperties) {
            if (!configurationProperties.containsKey(requiredProperty)) {
                throw new IllegalArgumentException("PSMPPLib: Configuration file doesn't contain required field: " + requiredProperty);
            }
        }
    }

    public PSmppServiceFactory buildServiceFactory() {
        return new PSmppServiceFactory();
    }
}