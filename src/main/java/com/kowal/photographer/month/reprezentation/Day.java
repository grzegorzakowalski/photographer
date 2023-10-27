package com.kowal.photographer.month.reprezentation;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Class that represents day of week
 */
@AllArgsConstructor
@Data
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
