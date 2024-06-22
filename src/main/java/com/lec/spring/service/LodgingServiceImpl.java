package com.lec.spring.service;


import com.lec.spring.domain.Lodging;
import com.lec.spring.repository.LodgingRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public List<Lodging> lodgingDetail(Long lodgingId) {
        return lodgingRepository.findLodgingById(lodgingId);
    }

    @Override
    public List<Lodging> lodgingName(Long lodgingId) {
        return lodgingRepository.findLodgingByName(lodgingId);
    }

    @Override
    public List<Lodging> getLodgingsByType(String type) {
        return lodgingRepository.findLodgingByType(type);
    }

    @Override
    public List<Lodging> getLodgingsByLocationAndType(String location, String type) { // 새로운 메서드 구현
        return lodgingRepository.findLodgingsByLocationAndType(location, type);
    }

}