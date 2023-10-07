package com.kowal.photographer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@AllArgsConstructor
@Data
@ToString
public class Day {
    private boolean isAvailable;
    private int dayOfWeekNumber;
    private int dateNumber;


}
