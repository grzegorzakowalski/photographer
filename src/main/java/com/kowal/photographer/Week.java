package com.kowal.photographer;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing week of month.
 */
@Data
@ToString
public class Week {
    private final List<Day> listOfDays;

    /**
     * Constructor which creates, all bout first week of month.
     * @param firstDayNumber first day of a week as day of month number.
     * @param monthLength month length.
     */
    public Week(Integer firstDayNumber, Integer monthLength){
        listOfDays = new ArrayList<>();
        for (int i = 1; i <= 7 && i + firstDayNumber <= monthLength; i++){
            listOfDays.add( new Day( i, firstDayNumber + i - 1));
        }
    }


    /**
     * Constructor creating only first week of month.
     * @param firstDayNumber first day of a week as day of month number.
     * @param monthLength month length.
     * @param dayOfWeekNumber number that represents which day of week is first day in this week. For example:
     *                        monday is 1,
     *                        thursday is 4.
     */
    public Week(Integer firstDayNumber, Integer monthLength, Integer dayOfWeekNumber){
        listOfDays = new ArrayList<>();
        for (int i = dayOfWeekNumber; i <= 7 && i <= monthLength; i++){
            listOfDays.add( new Day( i, firstDayNumber + i - 1));
        }
    }
}
