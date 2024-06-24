package com.lec.spring.service;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;

import java.util.List;

public interface ProviderService {
    List<ProvLodging> getLodgings();
    ProvLodging getAllDetails(int lodgingId);
    void saveLodging(ProvLodging lodging);

    List<ProvLodging> getLodgingsAndRoomsByUserId(Long userId);
}
