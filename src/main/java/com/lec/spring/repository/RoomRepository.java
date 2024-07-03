package com.lec.spring.repository;

import com.lec.spring.domain.Room;

import java.util.List;

public interface  RoomRepository {
    int createRoom(Room room);

    List<Room> findAll();

    Room findByRoomId(Long roomId);

    List<Room> findByLodgingId(Long lodgingId);

    void updateRoom(Room room);

    // roomId 관련된 post 삭제
    void deletePostsByRoomId(int roomId);

    // roomId와 관련된 booking 삭제
    void deleteBookingsByRoomId(int roomId);

    // roomId와 관련된 room 삭제
    void deleteRoom(int roomId);

    List<Room> findRoomsByLodgingId(Long lodgingId);
}