package net.proselyte.psmpplib.model;

import org.jsmpp.session.SMPPSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

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
