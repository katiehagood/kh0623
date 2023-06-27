package com.katiehagood.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.katiehagood.model.holiday.HolidayChecker;

public class RentalAgreement {

    private CheckoutCart cart;

    /**
     * Constructs rental agreement from checkout cart
     * 
     * @param cart
     */
    public RentalAgreement(CheckoutCart cart) {
        this.cart = cart;
    }

    /**
     * Gets checkout cart associated with this rental agreement
     * 
     * @return Checkout cart
     */
    public CheckoutCart getCart() {
        return cart;
    }

    /**
     * Gets due date of this rental
     * 
     * @return Due date
     */
    public LocalDate getDueDate() {
        LocalDate startDate = this.cart.getStartDate();
        return startDate.plusDays(this.cart.getNumDays());
    }

    /**
     * Gets daily rental charge
     * 
     * @return Daily rental charge
     */
    public BigDecimal getDailyRentalCharge() {
        return this.cart.getTool().getType().getDailyCharge();
    }

    /**
     * Gets the number of charged days during this rental.
     * If tool type isn't charged on weekends or holidays, it will not count those
     * days
     * 
     * @return Number of days charged
     */
    public int getChargeDays() {
        LocalDate startDate = this.cart.getStartDate();
        LocalDate endDate = this.getDueDate();

        int chargeDays = 0;

        // Current day doesn't count
        LocalDate date = startDate.plusDays(1);

        while (!date.isAfter(endDate)) {
            if (this.shouldChargeDay(date)) {
                chargeDays++;
            }
            date = date.plusDays(1);
        }

        return chargeDays;
    }

    /**
     * Helper method to determine if day should be charged
     * 
     * @param date Date during rental
     * @return True if date should be charged, False otherwise
     */
    private boolean shouldChargeDay(LocalDate date) {
        boolean chargeWeekends = this.cart.getTool().getType().hasWeekendCharge();
        boolean chargeHolidays = this.cart.getTool().getType().hasHolidayCharge();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        return !((!chargeWeekends && (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY))
                || (!chargeHolidays && HolidayChecker.isObservedHoliday(date)));

    }

    /**
     * Gets charge before discount is applied
     * @return Charge amount before discount
     */
    public BigDecimal getPreDiscountCharge() {
        int chargeDays = this.getChargeDays();
        BigDecimal dailyCharge = this.getDailyRentalCharge();
        return dailyCharge.multiply(new BigDecimal(chargeDays));
    }

    /**
     * Gets total discount on rental
     * @return Discount amount
     */
    public BigDecimal getDiscountAmount() {
        BigDecimal beforeDiscount = this.getPreDiscountCharge();
        BigDecimal percent = BigDecimal.valueOf(this.cart.getDiscount())
                .divide(BigDecimal.valueOf(100));
        return beforeDiscount.multiply(percent).setScale(2, RoundingMode.CEILING);

    }

    /**
     * Gets the final amount charged to the customer
     * @return Final amount
     */
    public BigDecimal getFinalAmount() {
        return this.getPreDiscountCharge().subtract(this.getDiscountAmount());
    }

    /**
     * String of receipt to show customer
     */
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
                        "Final charge: $%.2f\n",
                this.cart.getTool().getCode(),
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
                this.getFinalAmount());
    }
}
