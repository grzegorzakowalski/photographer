package com.kowal.photographer.repositorys;

import com.kowal.photographer.entities.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicturesRepository extends JpaRepository<Pictures,Long> {
}
