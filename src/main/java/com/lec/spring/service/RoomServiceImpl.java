package com.lec.spring.service;

import com.lec.spring.domain.Room;
import com.lec.spring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRoomDetails() {
        return roomRepository.findAllDetails();
    }
}
