package com.booking.support.exceptions;

public class ReservationApplException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ReservationApplException() {
        super();
    }

    public ReservationApplException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public ReservationApplException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public ReservationApplException(String arg0) {
        super(arg0);
    }

    public ReservationApplException(Throwable arg0) {
        super(arg0);
    }
}
