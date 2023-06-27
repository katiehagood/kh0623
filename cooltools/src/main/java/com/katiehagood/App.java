package com.katiehagood;

import java.util.Scanner;

import com.katiehagood.exceptions.CheckoutException;
import com.katiehagood.model.CheckoutCart;
import com.katiehagood.model.RentalAgreement;

/**
 * Cool Tools App
 *
 */
public class App 
{

    /**
     * Gets checkout info from the user
     * @param scanner CLI scanner
     * @return CheckoutCart if valid
     */
    public static CheckoutCart getCheckoutInfo(Scanner scanner) {
        System.out.println("Please enter your rental info");
        
        System.out.println("Tool Code:");
        String toolCode = scanner.nextLine();

        System.out.println("Rental day count:");
        int rentalDayCount = Integer.parseInt(scanner.nextLine());

        System.out.println("Discount Percent:");
        int discount = Integer.parseInt(scanner.nextLine());

        System.out.println("Check Out Date:");
        String checkOutDate = scanner.nextLine();

        try {
            return new CheckoutCart(toolCode, rentalDayCount, discount, checkOutDate);
        } catch (CheckoutException checkoutException) {
            System.out.println("Checkout unable to be completed.");
            System.out.println("Reason: " + checkoutException.getMessage());
        }
        return null;
    }

    /**
     * Entrypoint into CoolTools
     * @param args
     */
    public static void main( String[] args )
    {
        System.out.println("Welcome to Cool Tools!");

        boolean active = true;
        Scanner scanner = new Scanner(System.in);
        CheckoutCart cart = getCheckoutInfo(scanner);

        // Checkout failed; ask to try again
        while (active && cart == null) {
            System.out.println("Would you like to try again? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("y")) {
                cart = getCheckoutInfo(scanner);
            } else {
                active = false;
            }
        }

        // If checkout was successful, print rental agreement
        if (cart != null) {
            RentalAgreement ra = cart.generateRentalAgreement();
            System.out.println(ra.toString());
        }

        scanner.close();
    }
}
