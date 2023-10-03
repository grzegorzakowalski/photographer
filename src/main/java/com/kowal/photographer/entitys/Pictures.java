package com.kowal.photographer.entitys;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Pictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;

}
