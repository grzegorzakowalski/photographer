package com.kowal.photographer.services;

import com.kowal.photographer.entitys.Galery;
import com.kowal.photographer.repositorys.GaleryRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GaleryService {
    private final GaleryRepository galeryRepository;

    public GaleryService(GaleryRepository galeryRepository) {
        this.galeryRepository = galeryRepository;
    }

    public Galery findById(Long id){
        return galeryRepository.getReferenceById(id);
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

    public List<List<Galery>> FindAllAndSplitInHalf(){
        List<List<Galery>> result = new ArrayList<>();
        List<Galery> all = findAll();
        result.add( all.subList(0, all.size() / 2));
        result.add( all.subList(all.size() / 2 + 1, all.size() -1));
        return result;
    }
}
