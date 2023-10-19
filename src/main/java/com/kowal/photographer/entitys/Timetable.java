package com.kowal.photographer.entitys;

import lombok.Data;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * Encja przechowująca dane o planowanej/ zakończonej sesji zdjęciowej
 */
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
    private Boolean isDone;
}
