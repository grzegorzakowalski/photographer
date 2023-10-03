package com.kowal.photographer.services;

import com.kowal.photographer.entitys.Timetable;
import com.kowal.photographer.repositorys.ConfigRepository;
import com.kowal.photographer.repositorys.TimetableRepository;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;

    public TimetableService(TimetableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }
    @NotNull
    public Boolean add(Timetable entity, Integer size){
        if( timetableRepository.findAllByDate(entity.getDate()).size() >= size){
            return false;
        }
        if( entity.getId() != null){
            return false;
        }
        timetableRepository.save(entity);
        return true;
    }
    @NotNull
    public Boolean update(Timetable entity){
        if( timetableRepository.exists(Example.of(entity))){
            timetableRepository.save(entity);
            return true;
        }
    return false;
    }

    public List<List<Timetable>> getListForMonth(LocalDate localDate){
        List<List<Timetable>> result = new ArrayList<>();
        MonthService monthService = new MonthService(localDate);
        for( int i = 1; i <= monthService.getMonthLength(); i++ ){
            result.add(timetableRepository.findAllByDate(localDate.withDayOfMonth(i)));
        }
        return result;
    }

    public List<Boolean> getUnavailableListForMonth(LocalDate localDate, Integer maxSize){
        List<Boolean> result = new ArrayList<>();
        MonthService monthService = new MonthService(localDate);
        for (int i = 1; i <= monthService.getMonthLength(); i++){
            if( timetableRepository.findAllByDate(localDate.withDayOfMonth(i)).size() >= maxSize){
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }
}
