package com.kowal.photographer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Class that represents day of week
 */
@AllArgsConstructor
@Data
@ToString
public class Day {
    /**
     * Weekday number
     */
    private int dayOfWeekNumber;
    /**
     * Day of the month
     */
    private int dateNumber;


}
