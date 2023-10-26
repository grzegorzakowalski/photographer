package com.kowal.photographer.services;

import com.kowal.photographer.pojo.AddPhoto;
import com.kowal.photographer.entities.Timetable;
import com.kowal.photographer.entities.User;
import com.kowal.photographer.repositorys.PicturesRepository;
import com.kowal.photographer.repositorys.TimetableRepository;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service operating on timetable
 */
@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PicturesRepository picturesRepository;
    private final ConfigurationService configurationService;

    public TimetableService(TimetableRepository timetableRepository, UserRepository userRepository, UserService userService, PicturesRepository picturesRepository, ConfigurationService configurationService) {
        this.timetableRepository = timetableRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.picturesRepository = picturesRepository;
        this.configurationService = configurationService;
    }

    /**
     * Add timetable to db. It only adds, can't update.
     * @param entity which you want to add
     * @return true if operation was successfully executed, false otherwise.
     */
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

    /**
     * Method sets up list of bool for days of given month, where true means that day is full, and you can't book more meetings.
     * @param localDate param with month you want to operate.
     * @return Boolean list where true means day is full
     */
    public List<Boolean> getUnavailableListForMonth(LocalDate localDate){
        Integer maxSize = configurationService.getIntegerMaxPerDay();
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

    /**
     * Method returns list of timetables that are still to go. Whither it is confirmed or not.
     * @param isConfirmed  is session confirmed by photographer or no.
     * @return list of timetables that meet circumstances.
     */
    public List<Timetable> getNotDoneListByConfirmed(boolean isConfirmed){
        List<Timetable> allByConfirmed = timetableRepository.findAllByConfirmed(isConfirmed);
        return allByConfirmed.stream()
                .sorted(Comparator.comparing(Timetable::getDate))
                .filter(el -> !el.getIsDone())
                .toList();
    }

    /** Method adds pictures to timetable using AddPhoto object. Object from this class contains proper saved timetable entity, and new photo entity which doesn't matter is it saved or not.
     *
     * @param addPhoto object containing two entities: timetable, and pictures.
     */
    public void addPhoto(AddPhoto addPhoto){
        picturesRepository.save(addPhoto.getPictures());
        List<User> adminList = userService.getAdminListWithoutPicture(addPhoto.getPictures());
        adminList.forEach( admin ->{
            admin.getPictures().add(addPhoto.getPictures());
            userRepository.save(admin);
        });
        addPhoto.getTimetable().setIsDone(true);
        addPhoto.getTimetable().getOwner().getPictures().add(addPhoto.getPictures());
        userRepository.save(addPhoto.getTimetable().getOwner());
        timetableRepository.save(addPhoto.getTimetable());
    }

}
