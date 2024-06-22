package com.lec.spring.service;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import com.lec.spring.repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;

    @Autowired
    public ProviderServiceImpl(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public List<ProvLodging> getAllLodgingDetails() {
        return providerRepository.findAllDetails();
    }

    @Override
    public ProvLodging getLodgingById(int lodgingId) {
        return providerRepository.findLodgingById(lodgingId);
    }

    @Override
    public void saveLodging(ProvLodging lodging) {
        providerRepository.saveLodging(lodging);
    }

    // Room 관련 메서드 구현
//    @Override
//    public List<Room> getAllRoomDetails() {
//        return providerRepository.findAllRooms();
//    }
//
//    @Override
//    public Room getRoomById(Long roomId) {
//        return providerRepository.findRoomById(roomId);
//    }
//
//    @Override
//    public List<Room> getRoomsByLodgingId(Long lodgingId) {
//        return providerRepository.findRoomsByLodgingId(lodgingId);
//    }
}
