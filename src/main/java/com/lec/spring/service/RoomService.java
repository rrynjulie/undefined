package com.lec.spring.service;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RoomService {
    int createRoom(Room room);
    List<Room> findRoomsByLodgingId(Long lodgingId);
    Room findByRoomId(Long roomId);
    void updateRoom(Room room);
    void deleteRoom(int roomId);
}