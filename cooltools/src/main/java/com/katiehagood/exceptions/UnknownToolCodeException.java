package com.katiehagood.exceptions;

public class UnknownToolCodeException extends CheckoutException{

    public UnknownToolCodeException(String msg) {
        super(msg);
    }
    
}
