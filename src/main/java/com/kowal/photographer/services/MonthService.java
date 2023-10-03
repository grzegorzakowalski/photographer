package com.kowal.photographer.services;


import java.time.LocalDate;
import java.time.Month;

public class MonthService {
    private final LocalDate localDate;

    public MonthService(LocalDate localDate){
        this.localDate = LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth());
    }
    public String getMonthName(){
        return localDate.getMonth().name();
    }

    public Integer getFirstDayAsNumberOfWeekDay(){
        return localDate.withDayOfMonth(1).getDayOfWeek().getValue();
    }

    public Integer getMonthLength(){
        return localDate.lengthOfMonth();
    }

    public LocalDate firstDayOfMonth(){
        return localDate.withDayOfMonth(1);
    }

}
