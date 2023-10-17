package com.kowal.photographer.services;

import com.kowal.photographer.AddPhoto;
import com.kowal.photographer.entitys.Timetable;
import com.kowal.photographer.repositorys.PicturesRepository;
import com.kowal.photographer.repositorys.TimetableRepository;
import com.kowal.photographer.repositorys.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;
    private final PicturesRepository picturesRepository;

    public TimetableService(TimetableRepository timetableRepository, UserRepository userRepository, PicturesRepository picturesRepository) {
        this.timetableRepository = timetableRepository;
        this.userRepository = userRepository;
        this.picturesRepository = picturesRepository;
    }
    @NotNull
    public Boolean add(Timetable entity){
        if( entity.getId() != null){
            return false;
        }
        if( !entity.getOwner().getActive()){
            userRepository.save(entity.getOwner());
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

    public List<Timetable> getNotDoneListByConfirmed(boolean isConfirmed){
        List<Timetable> allByConfirmed = timetableRepository.findAllByConfirmed(isConfirmed);
        return allByConfirmed.stream()
                .sorted(Comparator.comparing(Timetable::getDate))
                .filter(el -> !el.getIsDone())
                .toList();
    }

    public void addPhoto(AddPhoto addPhoto){
        addPhoto.getTimetable().getOwner().getPictures().add(addPhoto.getPictures());
        userRepository.save(addPhoto.getTimetable().getOwner());
        timetableRepository.save(addPhoto.getTimetable());
    }

}
