package com.lec.spring.repository;

import com.lec.spring.domain.Room;

import java.util.List;

public interface RoomRepository {
    int create(Room room);

    List<Room> findAll();

    Room findByRoomId(Long roomId);

    List<Room> findByLodgingId(Long lodgingId);

    int update(Room room);

    int delete(Room room);
}