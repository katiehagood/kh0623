package com.katiehagood.model.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class NonWeekendHoliday extends Holiday{

    private int month;
    private int day;

    public NonWeekendHoliday(int month, int day) {
        this.month = month;
        this.day = day;
    }

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
