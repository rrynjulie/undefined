package com.lec.spring.service;

import com.lec.spring.domain.ProvLodging;

import java.util.List;

public interface ProviderService {
    List<ProvLodging> getAllLodgingDetails();
    ProvLodging getLodgingById(int lodgingId);
    void saveLodging(ProvLodging lodging);
}
