package com.katiehagood.model.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * A holiday that doesn't occur on a specific date, but is instead relative
 * to a position in the month. For example, Labor Day is the 1st Monday in
 * September.
 */
public class RelativeHoliday extends Holiday {

    private int month;
    private DayOfWeek dayOfWeek;
    private int weekInMonth;

    /**
     * Constructs a relative holiday
     * 
     * @param month       Month holiday occurs in
     * @param dayOfWeek   Day of week holiday occurs on
     * @param weekInMonth Week number holiday occurs on. For example, 1 would be on
     *                    first week in the month.
     */
    public RelativeHoliday(int month, DayOfWeek dayOfWeek, int weekInMonth) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.weekInMonth = weekInMonth;
    }

    /**
     * Gets when the holiday is observed in the given year
     * 
     * @param year
     * @return Date of holiday observance
     */
    @Override
    protected LocalDate getOccuranceInYear(int year) {
        LocalDate firstInMonth = LocalDate.of(year, this.month, 1);
        return firstInMonth.with(TemporalAdjusters.dayOfWeekInMonth(weekInMonth, dayOfWeek));
    }

}
