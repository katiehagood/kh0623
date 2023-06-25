package com.katiehagood.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.katiehagood.exceptions.InvalidDiscountException;
import com.katiehagood.exceptions.InvalidNumRentalDaysException;
import com.katiehagood.exceptions.InvalidStartDateException;
import com.katiehagood.exceptions.UnknownToolCodeException;
import com.katiehagood.model.tool.Tool;
import com.katiehagood.model.tool.ToolFactory;

public class CheckoutCart {

    private Tool tool;
    private int numDays;
    private int discount;
    private LocalDate startDate;


    public CheckoutCart(String toolCode, int numDays, int discount, String startDate) throws UnknownToolCodeException, InvalidDiscountException, InvalidStartDateException, InvalidNumRentalDaysException{
        validDiscount(discount);
        validNumDays(numDays);
        this.tool = new ToolFactory().getTool(toolCode);
        this.numDays = numDays;
        this.discount = discount;
        this.startDate = convertToDate(startDate);
    }
    
    private static void validDiscount(int discount) throws InvalidDiscountException{
        if (discount < 0 || discount > 100) {
            throw new InvalidDiscountException("%d discount is invalid".formatted(discount));
        }
    }

    private static void validNumDays(int numDays) throws InvalidNumRentalDaysException{
        if (numDays <= 0) {
            throw new InvalidNumRentalDaysException("%d is not a valid number of rental days".formatted(numDays));
        }
    }

    private static LocalDate convertToDate(String dateString) throws InvalidStartDateException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[M/]d/yyyy");

        try {
            return LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException dateTimeParseException) {
            throw new InvalidStartDateException("Could not parse date %s".formatted(dateString));
        }
    }
    
    public Tool getTool() {
        return tool;
    }

    public int getNumDays() {
        return numDays;
    }
    
    public int getDiscount() {
        return discount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
