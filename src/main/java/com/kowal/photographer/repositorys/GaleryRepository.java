package com.kowal.photographer.repositorys;

import com.kowal.photographer.entities.Galery;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GaleryRepository extends JpaRepository<Galery,Long> {
}
