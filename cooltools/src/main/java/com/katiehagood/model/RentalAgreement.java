package com.katiehagood.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

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
}
