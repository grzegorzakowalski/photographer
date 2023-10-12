package com.kowal.photographer.entitys;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    @ManyToOne
    @Valid
    private User owner;
    private Boolean confirmed;
    private String hour;
    @NotBlank
    private String description;

}
