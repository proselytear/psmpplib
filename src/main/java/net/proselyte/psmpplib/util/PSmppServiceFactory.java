package net.proselyte.psmpplib.util;

import net.proselyte.psmpplib.service.PSmppServiceImpl;

/**
 * Factory method implementation for {@link PSmppServiceImpl} class.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */
public class PSmppServiceFactory {
    private String smppHost;
    private String smppPort;
    private String smppSystemId;
    private String smppPassword;

    public PSmppServiceFactory() {
        this.smppHost = Configuration.configurationProperties.getProperty("smppHost");
        this.smppPort = Configuration.configurationProperties.getProperty("smppPort");
        this.smppSystemId = Configuration.configurationProperties.getProperty("smppSystemId");
        this.smppPassword = Configuration.configurationProperties.getProperty("smppPassword");
    }

    public PSmppServiceImpl getService() {
        return new PSmppServiceImpl(smppHost, Integer.parseInt(smppPort), smppSystemId, smppPassword);
    }
}