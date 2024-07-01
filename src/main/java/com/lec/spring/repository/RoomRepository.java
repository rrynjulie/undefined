package com.lec.spring.repository;

import com.lec.spring.domain.Room;

import java.util.List;

public interface  RoomRepository {
    int createRoom(Room room);

    List<Room> findAll();

    Room findByRoomId(Long roomId);

    List<Room> findByLodgingId(Long lodgingId);

    void updateRoom(Room room);

    void deleteRoom(int roomId);

    List<Room> findRoomsByLodgingId(Long lodgingId);
}