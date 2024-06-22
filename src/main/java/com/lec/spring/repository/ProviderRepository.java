package com.lec.spring.repository;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProviderRepository {
    List<ProvLodging> findAllDetails();
    ProvLodging findLodgingById(int lodgingId);
    void saveLodging(ProvLodging lodging);

    // 추가된 Room 관련 메서드
//    List<Room> findAllRooms();
//    Room findRoomById(Long roomId);
//    List<Room> findRoomsByLodgingId(Long lodgingId);
}
