package com.kowal.photographer.services;

import java.time.LocalDate;

/**
 * Serwis ułatwiający pracę z miesiącami
 */
public class MonthService {
    private final LocalDate localDate;

    public MonthService(LocalDate localDate){
        this.localDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
    }

    public Integer getFirstDayAsNumberOfWeekDay(){
        return localDate.withDayOfMonth(1).getDayOfWeek().getValue();
    }

    public Integer getMonthLength(){
        return localDate.lengthOfMonth();
    }
}
