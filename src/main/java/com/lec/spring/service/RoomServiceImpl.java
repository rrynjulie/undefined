package com.lec.spring.service;

import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Room;
import com.lec.spring.repository.RoomRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public int createRoom(Room room, Map<String, MultipartFile> files) {
        return 0;
    }

    @Override
    public List<Lodging> readLodgingListByUserId() {
        return null;
    }

    @Override
    public List<Room> readRoomList() {
        return roomRepository.findAll();
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
}