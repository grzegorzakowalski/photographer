package com.kowal.photographer.services;

import com.kowal.photographer.entities.Pictures;
import com.kowal.photographer.entities.Timetable;
import com.kowal.photographer.entities.User;
import com.kowal.photographer.pojo.AddPhoto;
import com.kowal.photographer.repositorys.PicturesRepository;
import com.kowal.photographer.repositorys.TimetableRepository;
import com.kowal.photographer.repositorys.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TimetableServiceTests {
    @Mock
    private TimetableRepository mockTimetableRepository;
    @Mock
    private ConfigurationService mockConfigurationService;

    @Mock
    private UserService mockUserService;

    @Mock
    private PicturesRepository mockPicturesRepository;

    @Mock
    private UserRepository mockUserRepository;

    @InjectMocks
    private TimetableService timetableService;

    @Test
    public void givenAlreadyExistingEntity_WillNotAddAndReturnFalse(){
        Timetable timetable = new Timetable();
        timetable.setId(1L);
        assertFalse(timetableService.add(timetable));
    }

    @Test
    public void givenEmptyEntity_WillThrowException(){
        Timetable timetable = new Timetable();
        try{
            timetableService.add(timetable);
            fail();
        }catch (Exception ignored){

        }
    }

    @Test
    public void givenOneDayAboveLimit_WillReturnTrueAtThatDay(){
        Mockito.when(mockConfigurationService.getIntegerMaxPerDay()).thenReturn(2);
        LocalDate localDate = LocalDate.now();
        Mockito.when(mockTimetableRepository.findAllByDate(localDate.withDayOfMonth(5))).thenReturn(List.of(new Timetable(),new Timetable(),new Timetable()));
        for (int i = 1; i <= localDate.lengthOfMonth(); i++) {
            if (i != 5) {
                Mockito.when(mockTimetableRepository.findAllByDate(localDate.withDayOfMonth(i))).thenReturn(new ArrayList<>());
            }
        }
        assertTrue(timetableService.getUnavailableListForMonth(localDate).get(4));
    }

    private List<Timetable> getTimetableListByConfirmed(Integer size, boolean isConfirmed){
        List<Timetable> result = new ArrayList<>();
        if( size < 1){
            return result;
        }
        for (Long i = 0L; i < size; i++){
            result.add(Timetable.builder().id(i).confirmed(isConfirmed).date(LocalDate.now()).isDone(false).build());
        }
        return result;
    }

    @Test
    public void givenOnlyConfirmedAndNotDoneList_WillReturnSameList(){
        List<Timetable> correctResult = getTimetableListByConfirmed(3, true);
        Mockito.when(mockTimetableRepository.findAllByConfirmed(true)).thenReturn(correctResult);
        List<Timetable> result = timetableService.getNotDoneListByConfirmed(true);
        for (int i = 0; i < 3; i++) {
            assertEquals(correctResult.get(i).toString(), result.get(i).toString());
        }
    }

    @Test
    public void givenOnlyNotConfirmedDoneList_SearchingForConfirmed_WillReturnEmptyList(){
        Mockito.when(mockTimetableRepository.findAllByConfirmed(true)).thenReturn(new ArrayList<>());
        assertEquals(0, timetableService.getNotDoneListByConfirmed(true).size());
    }

    @Test
    public void givenAddPhotoObject_WillSaveCorrectly(){
        Pictures picture = new Pictures();
        picture.setLink("www.google.pl");
        picture.setDescription("Google");
        User owner = User.builder().id(1L).active(true).role("ROLE_USER").password("yolo").username("foo@baa").phoneNumber("666").pictures(new ArrayList<>()).build();
        Mockito.when(mockPicturesRepository.save(picture)).thenReturn(picture);
        Timetable timetable = Timetable.builder()
                .date(LocalDate.now()).
                id(1L).confirmed(true).
                isDone(false).
                description("opis").
                hour("11")
                .owner(owner)
                .build();
        User admin = User.builder().id(2L).role("ROLE_ADMIN").pictures(new ArrayList<>()).build();
        AddPhoto addPhoto = new AddPhoto();
        addPhoto.setTimetable(timetable);
        addPhoto.setPictures(picture);
        Mockito.when(mockUserRepository.save(admin)).thenReturn(admin);
        Mockito.when(mockUserService.getAdminListWithoutPicture(picture)).thenReturn(List.of(admin));
        Mockito.when(mockUserRepository.save(owner)).thenReturn(owner);
        Mockito.when(mockTimetableRepository.save(timetable)).thenReturn(timetable);
        try{
            timetableService.addPhoto(addPhoto);
        } catch (Exception e){
            fail();
        }

    }
}
