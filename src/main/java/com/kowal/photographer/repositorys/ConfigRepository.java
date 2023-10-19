package com.kowal.photographer.repositorys;

import com.kowal.photographer.entitys.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Configuration,Long> {
    Configuration getDistinctByNameIsLike(String name);
}
