package com.lec.spring.service;

import com.lec.spring.domain.Room;

import java.util.List;

public interface RoomService {
    int createRoom(Room room);
    List<Room> findRoomsByLodgingId(Long lodgingId);
    Room findRoomByRoomId(Long roomId);
    void updateRoom(Room room);
    void deleteRoom(int roomId);
}