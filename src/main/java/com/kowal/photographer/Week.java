package com.kowal.photographer;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data
@ToString
public class Week {
    private final List<Day> listOfDays;

    public Week(Integer firstDayNumber, Integer monthLength){
        listOfDays = new ArrayList<>();
        for (int i = 1; i <= 7 && i + firstDayNumber <= monthLength; i++){
            listOfDays.add( new Day(true, i, firstDayNumber + i - 1));
        }
    }

    public Week(Integer firstDayNumber, Integer monthLength, Integer dayOfWeekNumber){
        listOfDays = new ArrayList<>();
        for (int i = dayOfWeekNumber; i <= 7 && i <= monthLength; i++){
            listOfDays.add( new Day(true, i, firstDayNumber + i - 1));
        }
    }
}
