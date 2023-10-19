package com.kowal.photographer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * Klasa reprezentująca dzień tygodnia.
 */
@AllArgsConstructor
@Data
@ToString
public class Day {
    /**
     * Numer dnia w tygodniu
     */
    private int dayOfWeekNumber;
    /**
     * Numer dnia w miesiącu
     */
    private int dateNumber;


}
