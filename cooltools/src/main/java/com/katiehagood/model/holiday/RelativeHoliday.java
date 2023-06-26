package com.katiehagood.model.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class RelativeHoliday extends Holiday {

    private int month;
    private DayOfWeek dayOfWeek;
    private int weekInMonth;

    public RelativeHoliday(int month, DayOfWeek dayOfWeek, int weekInMonth) {
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.weekInMonth = weekInMonth;
    }
    
    @Override
    protected LocalDate getOccuranceInYear(int year) {
        LocalDate firstInMonth = LocalDate.of(year, this.month, 1);
        return firstInMonth.with(TemporalAdjusters.dayOfWeekInMonth(weekInMonth, dayOfWeek));
    }
    
}
