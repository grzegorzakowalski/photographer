package com.kowal.photographer.repositorys;

import com.kowal.photographer.entities.Galery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GaleryRepository extends JpaRepository<Galery,Long> {
}
