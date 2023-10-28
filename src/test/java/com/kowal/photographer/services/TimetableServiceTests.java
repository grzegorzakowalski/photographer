package com.kowal.photographer.services;

import com.kowal.photographer.entities.Timetable;
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
}
