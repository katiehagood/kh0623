package com.katiehagood.model.chargetype;

import java.math.BigDecimal;

public class ChargeType {
    
    private String name;
    private BigDecimal dailyCharge;
    private boolean hasWeekdayCharge;
    private boolean hasWeekendCharge;
    private boolean hasHolidayCharge;
    
    /**
     * Constructs a charge type for a tool
     * @param name Name of charge type
     * @param dailyCharge Amount of money charged per day
     * @param hasWeekdayCharge If this type charges for weekday rentals
     * @param hasWeekendCharge If this type charges for weekend rentals
     * @param hasHolidayCharge If this type has a holiday charge
     */
    public ChargeType(String name, BigDecimal dailyCharge,
        boolean hasWeekdayCharge, boolean hasWeekendCharge,
        boolean hasHolidayCharge) {
        this.name = name;
        this.dailyCharge = dailyCharge;
        this.hasWeekdayCharge = hasWeekdayCharge;
        this.hasWeekendCharge = hasWeekendCharge;
        this.hasHolidayCharge = hasHolidayCharge;
    }
    
    /**
     * Gets name of charge type
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets daily charge amount
     * @return Daily charge
     */
    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    /**
     * Gets if type charges weekdays
     * @return True if charges weekdays, False otherwise
     */
    public boolean hasWeekdayCharge() {
        return hasWeekdayCharge;
    }

    /**
     * Gets if type charges weekends
     * @return True if charges weekdays, False otherwise
     */
    public boolean hasWeekendCharge() {
        return hasWeekendCharge;
    }

    /**
     * Gets if type charges holidays
     * @return True if charges holidays, False otherwise
     */
    public boolean hasHolidayCharge() {
        return hasHolidayCharge;
    }

}