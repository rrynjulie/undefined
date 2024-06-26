package com.lec.spring.service;

import com.lec.spring.domain.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RoomService {
    int createRoom(Room room);
    List<Room> readRoomList(Long lodgingId);
    Room readRoomDetail(Long roomId);
    Room findByRoomId(Long roomId);
    int updateRoom(Room room, Map<String, MultipartFile> files, Long[] delfile);
    int deleteRoom(Long roomId);
    List<Room> findRoomsByLodgingId(Long lodgingId);
    Room getRoomById(Long roomId);


}