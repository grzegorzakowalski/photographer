package com.kowal.photographer.entitys;

import lombok.Data;

import javax.persistence.*;

/**
 * Encja trzymająca linki wraz z opisami do zdjęć z sesji zdjęciowych
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
