package com.katiehagood.model.holiday;

import java.time.LocalDate;

public abstract class Holiday {

    protected abstract LocalDate getOccuranceInYear(int year);

    public boolean isObservedOn(LocalDate date) {
        LocalDate thisYearsOccurance = this.getOccuranceInYear(date.getYear());
        return date.equals(thisYearsOccurance);
    }

}
