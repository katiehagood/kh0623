package com.katiehagood.model.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class HolidayChecker {

    private static Holiday independenceDay = new NonWeekendHoliday(7, 4);
    private static Holiday laborDay = new RelativeHoliday(9, DayOfWeek.MONDAY, 1);

    public static boolean isObservedHoliday(LocalDate date) {
        
        List<Holiday> holidays = Arrays.asList(
            laborDay,
            independenceDay
        );

        for (Holiday holiday : holidays) {
            if (holiday.isObservedOn(date)) {
                return true;
            }
        }
        return false;

    }
    
}
