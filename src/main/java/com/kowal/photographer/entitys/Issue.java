package com.kowal.photographer.entitys;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Encja przechowująca zgłoszone problemu przez użytkowników
 */
@Entity
@Table(name = "issues")
@ToString
@Data
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    private boolean resolved;
}
