package com.kowal.photographer.services;

import com.kowal.photographer.entities.Galery;
import com.kowal.photographer.repositorys.GaleryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class GaleryServiceTests {

    @Mock
    private GaleryRepository mockGaleryRepository;

    @InjectMocks
    private GaleryService galeryService;

    private void setUpGaleryWithEqualInputs(){
        Mockito.when(mockGaleryRepository.findAll()).thenReturn( getEqualInputsList());
    }

    private List<Galery> getEqualInputsList(){
        return List.of(
                Galery.builder().id(1).name("tak1").description("nie").build(),
                Galery.builder().id(2).name("tak2").description("nie").build(),
                Galery.builder().id(3).name("tak3").description("nie").build(),
                Galery.builder().id(4).name("tak4").description("nie").build());
    }

    private void setUpGaleryWithoutEqualInputs(){
        Mockito.when(mockGaleryRepository.findAll()).thenReturn(getNotEqualInputsList());
    }
    private List<Galery> getNotEqualInputsList(){
        return List.of(
                Galery.builder().id(1).name("tak1").description("nie").build(),
                Galery.builder().id(2).name("tak2").description("nie").build(),
                Galery.builder().id(3).name("tak3").description("nie").build(),
                Galery.builder().id(4).name("tak4").description("nie").build(),
                Galery.builder().id(5).name("tak5").description("nie").build());
    }

    @Test
    public void givenEqualInputList_WillReturnListSplitInHalf(){
        setUpGaleryWithEqualInputs();
        List<Galery> correctInputsList = getEqualInputsList();
        List<List<Galery>> lists = galeryService.FindAllAndSplitInHalf();
        assertEquals( correctInputsList.get(0).getName(), lists.get(0).get(0).getName());
        assertEquals( correctInputsList.get(1).getName(), lists.get(0).get(1).getName());
        assertEquals( correctInputsList.get(2).getName(), lists.get(1).get(0).getName());
        assertEquals( correctInputsList.get(3).getName(), lists.get(1).get(1).getName());
    }

    @Test
    public void givenNotEqualInputList_WillReturnFirstListBigger(){
        setUpGaleryWithoutEqualInputs();
        List<Galery> correctInputsList = getNotEqualInputsList();
        List<List<Galery>> lists = galeryService.FindAllAndSplitInHalf();
        assertTrue(lists.get(0).size() > lists.get(1).size());
        assertEquals( correctInputsList.get(0).getName(), lists.get(0).get(0).getName());
        assertEquals( correctInputsList.get(1).getName(), lists.get(0).get(1).getName());
        assertEquals( correctInputsList.get(2).getName(), lists.get(0).get(2).getName());
        assertEquals( correctInputsList.get(3).getName(), lists.get(1).get(0).getName());
        assertEquals( correctInputsList.get(4).getName(), lists.get(1).get(1).getName());
    }
}
