package com.lec.spring.service;


import com.lec.spring.domain.Room;

import java.util.List;

public interface RoomService {
    Room findByRoomId(Long roomId);
}