package com.kowal.photographer.entitys;

import lombok.Data;

import javax.persistence.*;

/**
 * Encja będąca elementami galerii
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
