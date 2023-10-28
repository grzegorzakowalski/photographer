package com.kowal.photographer.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity holding which photos are used in galery
 */
@Entity
@Table(name = "galery")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Galery {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

}
