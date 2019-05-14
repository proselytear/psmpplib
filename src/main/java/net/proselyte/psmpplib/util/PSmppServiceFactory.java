package net.proselyte.psmpplib.util;

import net.proselyte.psmpplib.service.PSmppService;

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

    public PSmppService getService() {
        return new PSmppService(smppHost, Integer.parseInt(smppPort), smppSystemId, smppPassword);
    }
}