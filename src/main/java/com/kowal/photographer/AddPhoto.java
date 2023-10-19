package com.kowal.photographer;

import com.kowal.photographer.entitys.Pictures;
import com.kowal.photographer.entitys.Timetable;
import lombok.Data;
import lombok.ToString;

/**
 * Klasa, która przetrzymuje encje Timetable i Pictures.
 */
@Data
@ToString
public class AddPhoto {
    private Timetable timetable;
    private Pictures pictures;

}
