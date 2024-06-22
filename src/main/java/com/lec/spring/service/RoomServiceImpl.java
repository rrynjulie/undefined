package com.lec.spring.service;

import com.lec.spring.domain.Room;
import com.lec.spring.repository.RoomRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Room findByRoomId(Long roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        return room;
    }
}