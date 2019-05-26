package net.proselyte.psmpplib.service;

import net.proselyte.psmpplib.model.SessionState;

/**
 * SMPP service interface.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */
public interface PsmppService {

    String sendMessage(String sourceAddress, String destinationAddress, String message);

    SessionState getSessionState();

    boolean isAlive();

    String getSessionId();

    byte[] sendEnquireLink();

    void closeSession();
}
