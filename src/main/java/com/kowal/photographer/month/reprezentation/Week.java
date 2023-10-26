package com.kowal.photographer.month.reprezentation;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing week of month.
 */
@Data
public class Week {
    private final List<Day> listOfDays;

    /**
     * Constructor which creates, all bout first week of month.
     * @param firstDayNumber first day of a week as day of month number.
     * @param monthLength month length.
     */
    private Week(Integer firstDayNumber, Integer monthLength){
        listOfDays = new ArrayList<>();
        for (int i = 1; i <= 7 && i + firstDayNumber <= monthLength; i++){
            listOfDays.add( new Day( i, firstDayNumber + i - 1));
        }
    }

    public static Week getNotFirstWeekOfMonth(Integer firstDayNumber, Integer monthLength){
        return new Week(firstDayNumber, monthLength);
    }


    /**
     * Constructor creating only first week of month.
     * @param firstDayNumber first day of a week as day of month number.
     * @param monthLength month length.
     * @param dayOfWeekNumber number that represents which day of week is first day in this week. For example:
     *                        monday is 1,
     *                        thursday is 4.
     */
    private Week(Integer firstDayNumber, Integer monthLength, Integer dayOfWeekNumber){
        listOfDays = new ArrayList<>();
        for (int i = dayOfWeekNumber; i <= 7 && i <= monthLength; i++){
            listOfDays.add( new Day( i, firstDayNumber + i - 1));
        }
    }

    public static Week getFirstWeekOfMonth(Integer firstDayNumber, Integer monthLength, Integer dayOfWeekNumber){
        return new Week(firstDayNumber, monthLength, dayOfWeekNumber);
    }
}
