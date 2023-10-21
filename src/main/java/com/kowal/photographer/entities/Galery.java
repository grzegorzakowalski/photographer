package com.kowal.photographer.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Entity holding which photos are used in galery
 */
@Entity
@Table(name = "galery")
@Data
public class Galery {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

}
