package com.lec.spring.repository;

import com.lec.spring.domain.Room;

import java.util.List;

public interface RoomRepository {
    List<Room> findAllDetails();
}
