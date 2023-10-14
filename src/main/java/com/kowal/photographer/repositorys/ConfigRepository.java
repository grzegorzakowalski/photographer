package com.kowal.photographer.repositorys;

import com.kowal.photographer.entitys.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigRepository extends JpaRepository<Configuration,Long> {
    @Query("SELECT c.value FROM Configuration c WHERE c.name like 'max_per_day'")
    Configuration getMaxPerDay();

    @Query("SELECT c.value FROM Configuration c WHERE c.name like 'site_color'")
    Configuration getSiteColor();

}
