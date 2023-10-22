package com.kowal.photographer.entities;

import lombok.Data;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Entity holding data about planed/ done session
 */
@Entity
@Data
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne
    private User owner;
    private Boolean confirmed;
    private String hour;
    @NotBlank
    private String description;
    private Boolean isDone;
}
