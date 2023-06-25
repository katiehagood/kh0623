package com.katiehagood;

import org.junit.Test;

import com.katiehagood.model.CheckoutCart;
import com.katiehagood.model.RentalAgreement;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;

public class RentalAgreementTest {
    
    private String validToolCode;
    private String validStartDate;
    private int validNumDays;
    private int validDiscount;
    private CheckoutCart validCheckoutCart;

    @Before
    public void setUp() throws Exception{
        validToolCode = "CHNS";
        validStartDate = "6/25/2023";
        validNumDays = 5;
        validDiscount = 25;
        validCheckoutCart = new CheckoutCart(validToolCode, validNumDays, validDiscount, validStartDate);
    }
    
    @Test
    public void rentalAgreementTest_SuccessInit() throws Exception{
        RentalAgreement ra = new RentalAgreement(validCheckoutCart);
        assertEquals(ra.getCart().getNumDays(), validNumDays);
    }

    @Test
    public void getDueDateTest() throws Exception{
        RentalAgreement ra = new RentalAgreement(validCheckoutCart);
        assertEquals(ra.getDueDate(), LocalDate.of(2023, 6, 30));
        // Make sure start date isn't modified by plus days func
        assertEquals(ra.getCart().getStartDate(), LocalDate.of(2023,6,25));
    }

    @Test
    public void getDailyRentalChargeTest() throws Exception{
        RentalAgreement ra = new RentalAgreement(validCheckoutCart);
        assertEquals(ra.getDailyRentalCharge(), new BigDecimal("1.49"));
    }

    @Test
    public void getNumberOfChargeDays_NotWeekends() throws Exception{
        CheckoutCart cart = new CheckoutCart("CHNS", 14, 0, "06/14/2023");
        RentalAgreement ra = cart.generateRentalAgreement();

        assertEquals(10, ra.getChargeDays());
    }

    @Test
    public void getNumberOfChargeDays_InclWeekends() throws Exception{
        CheckoutCart cart = new CheckoutCart("LADW", 14, 0, "06/14/2023");
        RentalAgreement ra = cart.generateRentalAgreement();

        assertEquals(14, ra.getChargeDays());
    }

    @Test
    public void getPreDiscountCharge() throws Exception {
        CheckoutCart cart = new CheckoutCart("LADW", 14, 0, "06/14/2023");
        RentalAgreement ra = cart.generateRentalAgreement();
        assertEquals(new BigDecimal("27.86"), ra.getPreDiscountCharge());
    }

    @Test
    public void getDiscountAmount() throws Exception {
        CheckoutCart cart = new CheckoutCart("LADW", 14, 20, "06/14/2023");
        RentalAgreement ra = cart.generateRentalAgreement();
        assertEquals(new BigDecimal("5.58"), ra.getDiscountAmount());
    }

    @Test
    public void getFinalAmount() throws Exception {
        CheckoutCart cart = new CheckoutCart("LADW", 14, 20, "06/14/2023");
        RentalAgreement ra = cart.generateRentalAgreement();
        assertEquals(new BigDecimal("22.28"), ra.getFinalAmount());
    }
    
    @Test
    public void getAsString() throws Exception {
        CheckoutCart cart = new CheckoutCart("LADW", 14, 20, "06/14/2023");
        RentalAgreement ra = cart.generateRentalAgreement();

        String expected = """
        Tool code: LADW 
        Tool type: Ladder 
        Tool brand: Werner 
        Rental days: 14 
        Checkout date: 06/14/2023 
        Due date: 06/28/2023 
        Daily rental charge: $1.99 
        Charge days: 14 
        Pre-discount charge:  $27.86 
        Discount percent: 20% 
        Discount amount: $5.58 
        -------------------------
        Final charge: $22.28 
        """;
        assertEquals(expected, ra.toString());
    }

}
