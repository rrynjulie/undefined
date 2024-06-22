package com.lec.spring.service;


import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Room;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface RoomService {
    int createRoom(Room room, Map<String, MultipartFile> files);
    List<Lodging> readLodgingListByUserId();
    List<Room> readRoomList();
    Room readRoomDetail(Long roomId);
    Room findByRoomId(Long roomId);
    int updateRoom(Room room, Map<String, MultipartFile> files, Long[] delfile);
    int deleteRoom(Long roomId);
}