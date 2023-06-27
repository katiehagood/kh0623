package com.katiehagood.exceptions;

public class CheckoutException extends CoolToolsException{

    /**
     * Parent exception for all exceptions that could be thrown at checkout
     * @param msg
     */
    public CheckoutException(String msg) {
        super(msg);
    }
    
}
