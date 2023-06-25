package com.katiehagood.model.chargetype;

import java.math.BigDecimal;

public class ChargeType {
    
    private String name;
    private BigDecimal dailyCharge;
    private boolean hasWeekdayCharge;
    private boolean hasWeekendCharge;
    private boolean hasHolidayCharge;
    
    public ChargeType(String name, BigDecimal dailyCharge,
        boolean hasWeekdayCharge, boolean hasWeekendCharge,
        boolean hasHolidayCharge) {
        this.name = name;
        this.dailyCharge = dailyCharge;
        this.hasWeekdayCharge = hasWeekdayCharge;
        this.hasWeekendCharge = hasWeekendCharge;
        this.hasHolidayCharge = hasHolidayCharge;
    }
    
    public String getName() {
        return name;
    }

    public BigDecimal getDailyCharge() {
        return dailyCharge;
    }

    public boolean hasWeekdayCharge() {
        return hasWeekdayCharge;
    }

    public boolean hasWeekendCharge() {
        return hasWeekendCharge;
    }

    public boolean hasHolidayCharge() {
        return hasHolidayCharge;
    }

}