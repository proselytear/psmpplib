package net.proselyte.psmpplib.model;

import org.jsmpp.session.SMPPSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Extension of {@link SMPPSession} class.
 * Provides access to main SMPP functionality.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */
public class PsmppSession extends SMPPSession {
    private AtomicInteger atomicInteger = new AtomicInteger();

    /**
     * Send enquire link request
     */
    public void sendEnquireLink() {
        try {
            byte[] response = this.pduSender().sendEnquireLink(new ByteArrayOutputStream(atomicInteger.get()), atomicInteger.addAndGet(1));
            System.out.println(Arrays.toString(response));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
