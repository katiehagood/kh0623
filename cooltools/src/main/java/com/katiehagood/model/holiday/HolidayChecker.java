package com.katiehagood.model.holiday;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class HolidayChecker {
    
    /**
     * Utility class
     */
    private HolidayChecker() {
        throw new IllegalStateException("Utility class");
    }

    private static Holiday independenceDay = new NonWeekendHoliday(7, 4);
    private static Holiday laborDay = new RelativeHoliday(9, DayOfWeek.MONDAY, 1);

    /**
     * Checks if given date is an observed holiday
     * @param date Date to check
     * @return True if date is an observed holiday, False otherwise
     */
    public static boolean isObservedHoliday(LocalDate date) {

        List<Holiday> holidays = Arrays.asList(
                laborDay,
                independenceDay);

        for (Holiday holiday : holidays) {
            if (holiday.isObservedOn(date)) {
                return true;
            }
        }
        return false;

    }

}
