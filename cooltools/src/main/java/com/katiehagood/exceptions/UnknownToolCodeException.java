package com.katiehagood.exceptions;

public class UnknownToolCodeException extends CheckoutException{

    /**
     * Tool code entered is unknown
     * @param msg
     */
    public UnknownToolCodeException(String msg) {
        super(msg);
    }
    
}
