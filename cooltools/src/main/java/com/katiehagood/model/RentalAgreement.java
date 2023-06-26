package com.katiehagood.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentalAgreement {
    
    private CheckoutCart cart;

    public RentalAgreement(CheckoutCart cart) {
        this.cart = cart;
    }

    public CheckoutCart getCart() {
        return cart;
    }

    public LocalDate getDueDate() {
        LocalDate startDate = this.cart.getStartDate();
        return startDate.plusDays(this.cart.getNumDays());
    }

    public BigDecimal getDailyRentalCharge() {
        return this.cart.getTool().getType().getDailyCharge();
    }

    public int getChargeDays() {
        LocalDate startDate = this.cart.getStartDate();
        LocalDate endDate = this.getDueDate();

        int chargeDays = 0;

        // Current day doesn't count
        LocalDate date = startDate.plusDays(1);

        while(!date.isAfter(endDate)) {
            if (this.shouldChargeDay(date)) {
                chargeDays++;
            }
            date = date.plusDays(1);
        }

        return chargeDays;
    }

    private boolean shouldChargeDay(LocalDate date){
        boolean chargeWeekends = this.cart.getTool().getType().hasWeekendCharge();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        if (!chargeWeekends && (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
            return false;
        }

        return true;
    }

    public BigDecimal getPreDiscountCharge() {
        int chargeDays = this.getChargeDays();
        BigDecimal dailyCharge = this.getDailyRentalCharge();
        return dailyCharge.multiply(new BigDecimal(chargeDays));
    }

    public BigDecimal getDiscountAmount() {
        BigDecimal beforeDiscount = this.getPreDiscountCharge();
        BigDecimal percent = BigDecimal.valueOf(this.cart.getDiscount())
                .divide(BigDecimal.valueOf(100));
        return beforeDiscount.multiply(percent).setScale(2, RoundingMode.CEILING);

    }

    public BigDecimal getFinalAmount() {
        return this.getPreDiscountCharge().subtract(this.getDiscountAmount());
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return String.format(
            "Tool code: %s\n" +
            "Tool type: %s\n" +
            "Tool brand: %s\n" +
            "Rental days: %d\n" +
            "Checkout date: %s\n" +
            "Due date: %s\n" +
            "Daily rental charge: $%.2f\n" +
            "Charge days: %d\n" +
            "Pre-discount charge: $%.2f\n" +
            "Discount percent: %d%%\n" +
            "Discount amount: $%.2f\n" +
            "-------------------------\n" +
            "Final charge: $%.2f\n"
            ,this.cart.getTool().getCode(),
            this.cart.getTool().getType().getName(),
            this.cart.getTool().getBrand(),
            this.cart.getNumDays(),
            this.cart.getStartDate().format(formatter),
            this.getDueDate().format(formatter),
            this.getDailyRentalCharge(),
            this.getChargeDays(),
            this.getPreDiscountCharge(),
            this.cart.getDiscount(),
            this.getDiscountAmount(),
            this.getFinalAmount()
        );
    }
}
