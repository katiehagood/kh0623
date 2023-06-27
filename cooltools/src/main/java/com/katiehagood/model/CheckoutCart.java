package com.katiehagood.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.katiehagood.exceptions.CheckoutException;
import com.katiehagood.exceptions.InvalidDiscountException;
import com.katiehagood.exceptions.InvalidNumRentalDaysException;
import com.katiehagood.exceptions.InvalidStartDateException;
import com.katiehagood.model.tool.Tool;
import com.katiehagood.model.tool.ToolFactory;

public class CheckoutCart {

    private Tool tool;
    private int numDays;
    private int discount;
    private LocalDate startDate;

    /**
     * Creates a checkout cart from user input
     * 
     * @param toolCode  Tool code of rental
     * @param numDays   Number of days the customer will have tool. Must be over 0
     * @param discount  Percent discount to apply to rental
     * @param startDate Day rental starts
     * @throws CheckoutException If information given is not valid
     */
    public CheckoutCart(String toolCode, int numDays, int discount, String startDate) throws CheckoutException {
        validDiscount(discount);
        validNumDays(numDays);
        this.tool = new ToolFactory().getTool(toolCode);
        this.numDays = numDays;
        this.discount = discount;
        this.startDate = convertToDate(startDate);
    }

    /**
     * Helper method to check if discount is valid
     * 
     * @param discount Percent discount
     * @throws InvalidDiscountException If discount is invalid
     */
    private static void validDiscount(int discount) throws InvalidDiscountException {
        if (discount < 0 || discount > 100) {
            throw new InvalidDiscountException("%d discount is invalid".formatted(discount));
        }
    }

    /**
     * Helper method to check if number of rental days is valid
     * 
     * @param numDays Number of days
     * @throws InvalidNumRentalDaysException if number if days is invalid
     */
    private static void validNumDays(int numDays) throws InvalidNumRentalDaysException {
        if (numDays <= 0) {
            throw new InvalidNumRentalDaysException("%d is not a valid number of rental days".formatted(numDays));
        }
    }

    /**
     * Converts user inputed date string to date object
     * 
     * @param dateString Date in MM/dd/yy format, also supports M/d/y
     * @return Date object
     * @throws InvalidStartDateException If date cannot be parsed
     */
    private static LocalDate convertToDate(String dateString) throws InvalidStartDateException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[M/]d/yy");

        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidStartDateException(
                    "Could not parse date %s. Please make sure it is in MM/dd/yy format.".formatted(dateString));
        }
    }

    /**
     * Gets tool
     * 
     * @return Tool
     */
    public Tool getTool() {
        return tool;
    }

    /**
     * Gets number of days
     * 
     * @return Number of days
     */
    public int getNumDays() {
        return numDays;
    }

    /**
     * Gets discount as percentage
     * 
     * @return Discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * Gets start day of rental
     * 
     * @return Start date
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Generates a rental agreement from this checkout cart
     * 
     * @return Rental agreement
     */
    public RentalAgreement generateRentalAgreement() {
        return new RentalAgreement(this);
    }
}
