package com.booking.support.enums;

public enum ReturnCodeEnum {
    SYSTEM_ERROR, INVALID_PARAMETERS, INVALID_API_KEY, INVALID_AUTH_TOKEN, INVALID_CREDENTIALS, BECON_NOT_ADDED;

    public String value() {
        return name();
    }

    public static ReturnCodeEnum fromValue(String v) {
        return valueOf(v);
    }

}
