package net.proselyte.psmpplib.service;

import lombok.extern.slf4j.Slf4j;
import net.proselyte.psmpplib.model.PsmppSession;
import net.proselyte.psmpplib.model.SessionState;
import org.jsmpp.InvalidResponseException;
import org.jsmpp.PDUException;
import org.jsmpp.bean.Alphabet;
import org.jsmpp.bean.BindType;
import org.jsmpp.bean.ESMClass;
import org.jsmpp.bean.GeneralDataCoding;
import org.jsmpp.bean.MessageClass;
import org.jsmpp.bean.NumberingPlanIndicator;
import org.jsmpp.bean.RegisteredDelivery;
import org.jsmpp.bean.SMSCDeliveryReceipt;
import org.jsmpp.bean.TypeOfNumber;
import org.jsmpp.extra.NegativeResponseException;
import org.jsmpp.extra.ResponseTimeoutException;
import org.jsmpp.session.BindParameter;
import org.jsmpp.util.AbsoluteTimeFormatter;
import org.jsmpp.util.TimeFormatter;

import java.io.IOException;
import java.util.Date;

/**
 * Main service class of application.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Slf4j
public class PSmppServiceImpl implements PsmppService {
    private TimeFormatter timeFormatter;
    private PsmppSession pSmppSession;

    public PSmppServiceImpl() {
    }

    public PSmppServiceImpl(String host, Integer port, String systemId, String password) {
        timeFormatter = new AbsoluteTimeFormatter();
        this.pSmppSession = new PsmppSession();
        try {
            this.pSmppSession.connectAndBind(host, port,
                    new BindParameter(
                            BindType.BIND_TX,
                            systemId,
                            password,
                            "cp",
                            TypeOfNumber.UNKNOWN,
                            NumberingPlanIndicator.UNKNOWN,
                            null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends message to SMPP server.
     *
     * @param sourceAddress
     * @param destinationAddress
     * @param message
     * @return messageId
     */
    public String sendMessage(String sourceAddress, String destinationAddress, String message) {
        String messageId = null;
        try {
            messageId = this.pSmppSession.submitShortMessage("CMT",
                    TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.UNKNOWN, sourceAddress,
                    TypeOfNumber.INTERNATIONAL, NumberingPlanIndicator.UNKNOWN, destinationAddress,
                    new ESMClass(),
                    (byte) 0,
                    (byte) 1,
                    timeFormatter.format(new Date()), null, new RegisteredDelivery(SMSCDeliveryReceipt.DEFAULT),
                    (byte) 0,
                    new GeneralDataCoding(Alphabet.ALPHA_DEFAULT, MessageClass.CLASS1, false),
                    (byte) 0,
                    message.getBytes());

            log.info("IN sendMessage - message with ID: {} successfully sent", message);
        } catch (PDUException | ResponseTimeoutException | InvalidResponseException | NegativeResponseException | IOException e) {
            log.error("IN sendMessage - error occurred: {}", e.getCause());
        }

        return messageId;
    }

    /**
     * Returns state of current SMPP session
     */
    public SessionState getSessionState() {
        return SessionState.valueOf(pSmppSession.getSessionState().name());
    }

    public boolean isAlive() {
        return getSessionState().equals(SessionState.CLOSED);
    }

    public String getSessionId() {
        return pSmppSession.getSessionId();
    }

    public byte[] sendEnquireLink() {
        return this.pSmppSession.sendSmppEnquireLink();
    }

    public void closeSession() {
        this.pSmppSession.unbindAndClose();
    }
}
