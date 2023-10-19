package com.kowal.photographer.services;

import com.kowal.photographer.entitys.Galery;
import com.kowal.photographer.repositorys.GaleryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Serwis zajmujący się obsługą galerii
 */
@Service
public class GaleryService {
    private final GaleryRepository galeryRepository;

    public GaleryService(GaleryRepository galeryRepository) {
        this.galeryRepository = galeryRepository;
    }

    public void add(Galery galery){
        try {
            galeryRepository.getReferenceById(galery.getId());
        } catch ( EntityNotFoundException e){
            galeryRepository.save(galery);
        }
    }

    public List<Galery> findAll(){
        return galeryRepository.findAll();
    }

    /**
     * @return Wszystkie zdjęcia w galerii podzielone na 2 listy (na pół)
     */
    public List<List<Galery>> FindAllAndSplitInHalf(){
        List<List<Galery>> result = new ArrayList<>();
        List<Galery> all = findAll();
        result.add( all.subList(0, all.size() / 2 ));
        result.add( all.subList(all.size() / 2, all.size() ));
        return result;
    }
}
