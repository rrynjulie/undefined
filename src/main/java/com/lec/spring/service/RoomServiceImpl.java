package com.lec.spring.service;

import com.lec.spring.domain.Room;
import com.lec.spring.repository.ProviderRepository;
import com.lec.spring.repository.RoomRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(SqlSession sqlSession) {
        roomRepository = sqlSession.getMapper(RoomRepository.class);
    }

    @Override
    public void createRoom(Room room) {
        roomRepository.createRoom(room);
    }

    @Override
    public List<Room> readRoomList(Long lodgingId) {
        return roomRepository.findByLodgingId(lodgingId);
    }

    @Override
    public Room readRoomDetail(Long roomId) {
        return null;
    }

    @Override
    public Room findByRoomId(Long roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        return room;
    }

    @Override
    public int updateRoom(Room room, Map<String, MultipartFile> files, Long[] delfile) {
        return 0;
    }

    @Override
    public int deleteRoom(Long roomId) {
        return 0;
    }

    @Override
    public List<Room> findRoomsByLodgingId(Long lodgingId) {
        return roomRepository.findRoomsByLodgingId(lodgingId);
    }

    @Override
    public Room getRoomById(Long roomId) {
        return roomRepository.findById(roomId);
    }
}