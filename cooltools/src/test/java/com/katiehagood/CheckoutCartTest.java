package com.katiehagood;

import org.junit.Test;

import com.katiehagood.exceptions.InvalidDiscountException;
import com.katiehagood.exceptions.InvalidNumRentalDaysException;
import com.katiehagood.exceptions.UnknownToolCodeException;
import com.katiehagood.model.CheckoutCart;
import com.katiehagood.model.RentalAgreement;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;

public class CheckoutCartTest {

    private String validToolCode;
    private String validStartDate;
    private int validNumDays;
    private int validDiscount;

    @Before
    public void setUp() {
        validToolCode = "CHNS";
        validStartDate = "6/25/23";
        validNumDays = 5;
        validDiscount = 25;
    }
    
    @Test
    public void checkoutCartTest_SuccessInit() throws Exception{
         // Instantiating a tool with an unknown tool code will throw exception
         CheckoutCart cart = new CheckoutCart(validToolCode, validNumDays, validDiscount, validStartDate);
         assertEquals(cart.getTool().getType().getDailyCharge(), new BigDecimal("1.49"));
         assertEquals(cart.getNumDays(), validNumDays);
         assertEquals(cart.getStartDate(), LocalDate.of(2023,6,25));
         assertEquals(cart.getDiscount(), validDiscount);
    }
    
    @Test(expected = UnknownToolCodeException.class)
    public void checkoutCartTest_UnknownToolCode() throws Exception{
         // Instantiating a tool with an unknown tool code will throw exception
         CheckoutCart cart = new CheckoutCart("FREETOOLZ", validNumDays, validDiscount, validStartDate);
    }

    @Test(expected = InvalidDiscountException.class)
    public void checkoutCartTest_NegativeDiscount() throws Exception{
         // Instantiating a tool with an unknown tool code will throw exception
         CheckoutCart cart = new CheckoutCart(validToolCode, validNumDays, -5, validStartDate);
    }

    @Test(expected = InvalidDiscountException.class)
    public void checkoutCartTest_Over100Discount() throws Exception{
         // Instantiating a tool with an unknown tool code will throw exception
         CheckoutCart cart = new CheckoutCart(validToolCode, validNumDays, 101, validStartDate);
    }

    @Test(expected = InvalidNumRentalDaysException.class)
    public void checkoutCartTest_0RentalDays() throws Exception{
         // Instantiating a tool with an unknown tool code will throw exception
         CheckoutCart cart = new CheckoutCart(validToolCode, 0, validDiscount, validStartDate);
    }

    @Test
    public void generateRentalAgreementTest() throws Exception{
        CheckoutCart cart = new CheckoutCart(validToolCode, validNumDays, validDiscount, validStartDate);
        RentalAgreement ra = cart.generateRentalAgreement();
        assertEquals(ra.getCart().getNumDays(), validNumDays);
    }

}
