package com.katiehagood.exceptions;

public class InvalidNumRentalDaysException extends CheckoutException{

    /**
     * Number of rental days is invalid
     * @param msg
     */
    public InvalidNumRentalDaysException(String msg) {
        super(msg);
    }
    
}
