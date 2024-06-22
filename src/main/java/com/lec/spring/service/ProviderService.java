package com.lec.spring.service;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;

import java.util.List;

public interface ProviderService {
    List<ProvLodging> getAllLodgingDetails();
    ProvLodging getLodgingById(int lodgingId);
    void saveLodging(ProvLodging lodging);

    // Room 관련 메서드
//    List<Room> getAllRoomDetails();
//    Room getRoomById(Long roomId);
//    List<Room> getRoomsByLodgingId(Long lodgingId);
}
