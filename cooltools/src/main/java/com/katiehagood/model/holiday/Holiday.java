package com.katiehagood.model.holiday;

import java.time.LocalDate;

/**
 * 
 */
public abstract class Holiday {

    /**
     * Abstract method that gets when the holiday is observed in the given year
     * 
     * @param year
     * @return Date of holiday observance
     */
    protected abstract LocalDate getOccuranceInYear(int year);

    /**
     * Checks if given date is when the holiday is observed
     * @param date Date to check
     * @return True if date is this observed holiday, False otherwise
     */
    public boolean isObservedOn(LocalDate date) {
        LocalDate thisYearsOccurance = this.getOccuranceInYear(date.getYear());
        return date.equals(thisYearsOccurance);
    }

}
