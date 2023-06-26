package com.katiehagood.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

public class HolidayChecker {

    public static boolean isObservedHoliday(LocalDate date) {
        int year = date.getYear();

        // Check if labor day
        LocalDate laborDay = LocalDate.of(year, Month.SEPTEMBER, 1)
                .with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        if (date.equals(laborDay)) {
            return true;
        }

        // Check if independence day
        LocalDate independenceDay = LocalDate.of(year, Month.JULY, 4);
        if (independenceDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            independenceDay = independenceDay.minusDays(1);
        } else if (independenceDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            independenceDay = independenceDay.plusDays(1);
        }
        
        if (date.equals(independenceDay)) {
            return true;
        }

        return false;

    }
    
}
