package com.kowal.photographer.pojo;

import com.kowal.photographer.entities.Pictures;
import com.kowal.photographer.entities.Timetable;
import lombok.Data;
import lombok.ToString;

/**
 * Class which holds timetable and pictures entities
 */
@Data
public class AddPhoto {
    private Timetable timetable;
    private Pictures pictures;

}
