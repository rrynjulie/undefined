package com.lec.spring.service;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;

import java.util.List;

public interface ProviderService {
    List<ProvLodging> getLodgings(Long userId);
    ProvLodging getAllDetails(int lodgingId);
    void saveLodging(ProvLodging lodging);

    List<ProvLodging> getLodgingsAndRoomsByUserId(Long userId);

    void updateLodging(ProvLodging lodging);

    void deleteLodging(int lodgingId);

}
