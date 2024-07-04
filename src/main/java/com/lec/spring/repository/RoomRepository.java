package com.lec.spring.repository;

import com.lec.spring.domain.Room;

import java.util.List;

public interface  RoomRepository {
    int createRoom(Room room);
    List<Room> findRoomsByLodgingId(Long lodgingId);
    Room findRoomByRoomId(Long roomId);
    void updateRoom(Room room);
    // roomId와 관련된 room 삭제
    void deleteRoom(int roomId);
}