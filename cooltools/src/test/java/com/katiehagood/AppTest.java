package com.katiehagood;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.katiehagood.exceptions.CheckoutException;
import com.katiehagood.exceptions.InvalidDiscountException;
import com.katiehagood.model.CheckoutCart;
import com.katiehagood.model.RentalAgreement;

/**
 * Unit tests required in specifications
 */
public class AppTest 
{

    @Test(expected = InvalidDiscountException.class)
    public void test1() throws CheckoutException
    {
        // This will fail because the discount is over 100
        CheckoutCart cart = new CheckoutCart(
            "JAKR",
            5,
            101,
            "9/3/15"
        );

    }

    @Test
    public void test2() throws CheckoutException
    {
        // 4th of July observed on 7/3
        // Ladders charge on weekends
        CheckoutCart cart = new CheckoutCart(
            "LADW",
            3,
            10,
            "7/2/20"
        );
        RentalAgreement ra = cart.generateRentalAgreement();
        
        String expected = """
        Tool code: LADW 
        Tool type: Ladder 
        Tool brand: Werner 
        Rental days: 3 
        Checkout date: 07/02/20 
        Due date: 07/05/20 
        Daily rental charge: $1.99 
        Charge days: 2 
        Pre-discount charge: $3.98 
        Discount percent: 10% 
        Discount amount: $0.40 
        -------------------------
        Final charge: $3.50 
        """;
        assertEquals(expected, ra.toString());
    }

    @Test
    public void test3() throws CheckoutException
    {
        // Independence day observed 7/6
        // Chainsaws don't have a weekend charge
        CheckoutCart cart = new CheckoutCart(
            "CHNS",
            5,
            25,
            "7/2/15"
        );
        RentalAgreement ra = cart.generateRentalAgreement();
        
        String expected = """
        Tool code: CHNS 
        Tool type: Chainsaw 
        Tool brand: Stihl 
        Rental days: 5 
        Checkout date: 07/02/15 
        Due date: 07/07/15 
        Daily rental charge: $1.49 
        Charge days: 2 
        Pre-discount charge: $2.98 
        Discount percent: 25% 
        Discount amount: $0.75 
        -------------------------
        Final charge: $2.23 
        """;
        assertEquals(expected, ra.toString());
    }

    @Test
    public void test4() throws CheckoutException
    {
        // Labor day observed 9/7
        // Jackhamers don't have weekend charge
         CheckoutCart cart = new CheckoutCart(
            "JAKD",
            6,
            0,
            "9/3/15"
        );
        RentalAgreement ra = cart.generateRentalAgreement();
        
        String expected = """
        Tool code: JAKD 
        Tool type: Jackhammer 
        Tool brand: DeWalt 
        Rental days: 6 
        Checkout date: 09/03/15 
        Due date: 09/09/15 
        Daily rental charge: $2.99 
        Charge days: 3 
        Pre-discount charge: $8.97 
        Discount percent: 0% 
        Discount amount: $0.00 
        -------------------------
        Final charge: $8.97 
        """;
        assertEquals(expected, ra.toString());
    }

    
    @Test
    public void test5() throws CheckoutException
    {
        // Independence day obseved 7/3
        // Jackhamers don't have weekend charge
        CheckoutCart cart = new CheckoutCart(
            "JAKR",
            9,
            0,
            "7/2/15"
        );
        RentalAgreement ra = cart.generateRentalAgreement();
        
        String expected = """
        Tool code: JAKR 
        Tool type: Jackhammer 
        Tool brand: Ridgid 
        Rental days: 9 
        Checkout date: 07/02/15
        Due date: 07/11/15 
        Daily rental charge: $2.99 
        Charge days: 6 
        Pre-discount charge: $17.94 
        Discount percent: 0% 
        Discount amount: $0.00 
        -------------------------
        Final charge: $17.94 
        """;
        assertEquals(expected, ra.toString());
    }

    
    @Test
    public void test6() throws CheckoutException
    {
        // Independence day obseved 7/3
        // Jackhamers don't have weekend charge
        CheckoutCart cart = new CheckoutCart(
            "JAKR",
            4,
            50,
            "7/2/20"
        );
        RentalAgreement ra = cart.generateRentalAgreement();
        
        String expected = """
        Tool code: JAKR 
        Tool type: Jackhammer 
        Tool brand: Ridgid 
        Rental days: 4 
        Checkout date: 07/02/20
        Due date: 07/06/20
        Daily rental charge: $2.99 
        Charge days: 1 
        Pre-discount charge: $2.99 
        Discount percent: 50% 
        Discount amount: $1.50 
        -------------------------
        Final charge: $1.49 
        """;
        assertEquals(expected, ra.toString());
    }
}
