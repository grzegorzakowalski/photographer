package com.kowal.photographer.services;

import com.kowal.photographer.entities.Galery;
import com.kowal.photographer.repositorys.GaleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GaleryService {
    private final GaleryRepository galeryRepository;


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
     * Splits in half all items from galery to two lists.
     * @return list containing two sub lists.
     */
    public List<List<Galery>> FindAllAndSplitInHalf(){
        List<List<Galery>> result = new ArrayList<>();
        List<Galery> all = findAll();
        if( all.size() % 2 == 0) {
            result.add(all.subList(0, all.size() / 2));
            result.add(all.subList(all.size() / 2, all.size()));
        } else{
            result.add(all.subList(0, all.size() / 2 + 1));
            result.add(all.subList(all.size() / 2 + 1, all.size()));
        }
        return result;
    }
}
