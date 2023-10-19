package com.kowal.photographer.services;

import com.kowal.photographer.AddPhoto;
import com.kowal.photographer.entitys.Timetable;
import com.kowal.photographer.entitys.User;
import com.kowal.photographer.repositorys.PicturesRepository;
import com.kowal.photographer.repositorys.TimetableRepository;
import com.kowal.photographer.repositorys.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Serwis zajmujący się obsługą terminów na sesję zdjęciową.
 */
@Service
public class TimetableService {
    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final PicturesRepository picturesRepository;

    public TimetableService(TimetableRepository timetableRepository, UserRepository userRepository, UserService userService, PicturesRepository picturesRepository) {
        this.timetableRepository = timetableRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.picturesRepository = picturesRepository;
    }

    /**
     * Dodaje termin podany jako parametr do bazy danych.
     * @param entity
     * @return true, jeżeli udało się dodać termin do bazy. False w wypadku gdy istnieje już termin o tym id w bazie.
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
     * Metoda zwracająca listę dni, w których ilość dostępnych terminów jest już pełna.
     * @param localDate  parametr potrzebny, aby wydobyć miesiąc, na którym ma być użyta metoda
     * @param maxSize  maksymalna ilość terminów w dniu 
     * @return listę, która ma w sobie wszystkie dni danego miesiąca. Wartość true dla dni, które posiadają pełną ilość terminów, false w innym przypadku.
     */
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

    /** Metoda zwraca listę terminów, które nie mają dodanych zdjęć w zależności czy są potwierdzone, czy nie.
     * 
     * @param isConfirmed  czy sesja zdjęciowa została już potwierdzona przez fotografa 
     * @return Listę terminów spełniających warunki.
     */
    public List<Timetable> getNotDoneListByConfirmed(boolean isConfirmed){
        List<Timetable> allByConfirmed = timetableRepository.findAllByConfirmed(isConfirmed);
        return allByConfirmed.stream()
                .sorted(Comparator.comparing(Timetable::getDate))
                .filter(el -> !el.getIsDone())
                .toList();
    }

    /** Metoda dodaje zdjęcia do sesji zdjęciowej.
     *
     * @param addPhoto  obiekt przetrzymujący elementy encji Timetable i Pictures
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
