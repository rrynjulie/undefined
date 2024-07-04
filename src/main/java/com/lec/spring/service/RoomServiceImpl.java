package com.lec.spring.service;

import com.lec.spring.domain.Room;
import com.lec.spring.repository.RoomRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(SqlSession sqlSession) {
        roomRepository = sqlSession.getMapper(RoomRepository.class);
    }

    @Override
    public int createRoom(Room room) {
        return roomRepository.createRoom(room);
    }

    @Override
    public List<Room> findRoomsByLodgingId(Long lodgingId) {
        return roomRepository.findRoomsByLodgingId(lodgingId);
    }

    @Override
    public Room findByRoomId(Long roomId) {
        return roomRepository.findByRoomId(roomId);
    }

    @Override
    public void updateRoom(Room room) {
        roomRepository.updateRoom(room);
    }

    // roomId와 관련된 room 삭제
    @Override
    public void deleteRoom(int roomId) {
        roomRepository.deleteRoom(roomId);
    }
}