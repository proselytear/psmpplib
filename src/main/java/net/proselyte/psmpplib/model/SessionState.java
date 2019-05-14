package net.proselyte.psmpplib.model;

public enum SessionState {
    OPEN,
    BOUND_TX,
    BOUND_RX,
    BOUND_TRX,
    UNBOUND,
    CLOSED,
    OUTBOUND;

    private SessionState() {
    }

    public boolean isBound() {
        return this.equals(BOUND_RX) || this.equals(BOUND_TX) || this.equals(BOUND_TRX);
    }

    public boolean isTransmittable() {
        return this.equals(BOUND_TX) || this.equals(BOUND_TRX);
    }

    public boolean isReceivable() {
        return this.equals(BOUND_RX) || this.equals(BOUND_TRX);
    }
}
