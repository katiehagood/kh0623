package com.katiehagood.exceptions;

public class InvalidDiscountException extends CheckoutException{

    public InvalidDiscountException(String msg) {
        super(msg);
    }
    
}
