package com.katiehagood.model.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * A holiday that when it falls on a weekend, is observed on the closest weekday
 */
public class NonWeekendHoliday extends Holiday {

    private int month;
    private int day;

    /**
     * Constructs a non-weekend holiday
     * @param month Month holiday is in
     * @param day Actual day of holiday
     */
    public NonWeekendHoliday(int month, int day) {
        this.month = month;
        this.day = day;
    }

    /**
     * Gets when the holiday is observed in the given year
     * 
     * @param year
     * @return Date of holiday observance
     */
    @Override
    protected LocalDate getOccuranceInYear(int year) {
        LocalDate actualDay = LocalDate.of(year, month, day);

        // Adjust so it doesn't fall on weekend
        if (actualDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return actualDay.minusDays(1);
        } else if (actualDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return actualDay.plusDays(1);
        } else {
            return actualDay;
        }
    }

}
