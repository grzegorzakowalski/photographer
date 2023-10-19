package com.kowal.photographer.repositorys;

import com.kowal.photographer.entitys.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable,Long> {

    List<Timetable> findAllByDate(LocalDate date);
    List<Timetable> findAllByConfirmed(boolean isConfirmed);
}
