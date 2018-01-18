package com.booking.support.exceptions;

public class PoiApplException extends RuntimeException{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public PoiApplException() {
        super();
    }

    public PoiApplException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public PoiApplException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public PoiApplException(String arg0) {
        super(arg0);
    }

    public PoiApplException(Throwable arg0) {
        super(arg0);
    }
}
