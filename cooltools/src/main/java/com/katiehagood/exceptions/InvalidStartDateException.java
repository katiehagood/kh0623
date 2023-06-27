package com.katiehagood.exceptions;

public class InvalidStartDateException extends CheckoutException{

    /**
     * Rental start date could not be parsed or is otherwise invalid
     * @param msg
     */
    public InvalidStartDateException(String msg) {
        super(msg);
    }
    
}
