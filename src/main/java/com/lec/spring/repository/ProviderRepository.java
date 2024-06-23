package com.lec.spring.repository;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProviderRepository {
    List<ProvLodging> findAllDetails();
    ProvLodging findLodgingById(int lodgingId);
    void saveLodging(ProvLodging lodging);

    List<ProvLodging> findByUserId(Long userId);

}
