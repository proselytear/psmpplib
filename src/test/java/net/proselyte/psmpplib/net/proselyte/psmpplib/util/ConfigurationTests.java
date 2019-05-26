package net.proselyte.psmpplib.net.proselyte.psmpplib.util;

import net.proselyte.psmpplib.util.Configuration;
import net.proselyte.psmpplib.util.PSmppServiceFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationTests {

    @Test(expected = IllegalArgumentException.class)
    public void testConfigurationFileNotFound() {
        new Configuration("INCORRECT_PROPERTIES_FILE_LOCATION");
    }

    @Test
    public void testConfigurationFileIncorrectProperties() {
        try {
            new Configuration("src/test/resources/smpp-incorrect.properties");
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("PSMPPLib: Configuration file doesn't contain required field: smppHost"));
        }
    }

    @Test
    public void testConfigurationInitializedSuccess() {
        Configuration configuration = new Configuration("src/test/resources/smpp.properties");
        assertNotNull(configuration);
    }

    @Test
    public void testBuildServiceFactorySuccess(){
        Configuration configuration = new Configuration("src/test/resources/smpp.properties");
        PSmppServiceFactory smppServiceFactory = configuration.buildServiceFactory();
        assertNotNull(smppServiceFactory);
    }
}
