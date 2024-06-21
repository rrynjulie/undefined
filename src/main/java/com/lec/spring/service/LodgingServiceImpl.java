package com.lec.spring.service;


import com.lec.spring.domain.Lodging;
import com.lec.spring.repository.LodgingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LodgingServiceImpl implements LodgingService {

    private final LodgingRepository lodgingRepository;

    public LodgingServiceImpl(LodgingRepository lodgingRepository) {
        this.lodgingRepository = lodgingRepository;
    }

    @Override
    public List<Lodging> getLodgingsByLocation(String location) {
        return lodgingRepository.findLodgingsByLocation(location);
    }

//    @Autowired
//    private LodgingRepository lodgingRepository;
//
//    @Override
//    public List<Lodging> getAllLodgingDetails() {
//        return lodgingRepository.findAllLodgingDetails();
//    }

}