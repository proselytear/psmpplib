# PSMPPLib - wrapper for JSMPP (https://jsmpp.org/) library.

## SMPP server connection properties are automatically downloaded from smpp.properties file
## that should be created in client application

# Required properties:

smppHost -> host (localhost, IP address)
smppPort -> port
smppSystemId -> systemId
smppPassword -> password to get access

## How to use:

1. Clone project: https://github.com/proselytear/psmpplib
2. Run command: mvn clean install
3. Import psmpplib-1.0.0-jar-with-dependencies in your client application
4. Code example:

```java
        import net.proselyte.psmpplib.service.PSmppService;
        import net.proselyte.psmpplib.util.Configuration;
        ...
          PSmppService service = new Configuration().buildSessionFactory().getService();
          service.sendMessage("TEST", "TEST", "TEST");
          service.sendEnquireLink();
          service.closeSession();
        ...
```

## Environment:
1. Java 8+
2. Maven 3+
3. git