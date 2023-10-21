package com.kowal.photographer.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity holding links, and description of pictures done in session
 */
@Entity
@Data
public class Pictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String description;
}
