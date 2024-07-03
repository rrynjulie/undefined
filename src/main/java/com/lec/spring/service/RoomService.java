package com.lec.spring.service;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RoomService {
    int createRoom(Room room);
    List<Room> readRoomList(Long lodgingId);
    Room readRoomDetail(Long roomId);
    Room findByRoomId(Long roomId);
    void updateRoom(Room room);
    List<Room> findRoomsByLodgingId(Long lodgingId);
    Room getRoomById(Long roomId);

    void deletePostsByRoomId(int roomId);

    void deleteBookingsByRoomId(int roomId);

    void deleteRoom(int roomId);

}