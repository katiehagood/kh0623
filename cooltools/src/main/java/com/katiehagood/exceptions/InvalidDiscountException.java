package com.katiehagood.exceptions;

public class InvalidDiscountException extends CheckoutException{

    /**
     * Discount entered is invalid
     * @param msg
     */
    public InvalidDiscountException(String msg) {
        super(msg);
    }
    
}
