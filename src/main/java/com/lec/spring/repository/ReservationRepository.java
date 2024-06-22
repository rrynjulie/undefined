package com.lec.spring.repository;

import com.lec.spring.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationRepository {
    List<Reservation> findAll();

    Reservation findById(int reservationId);

    void insert(Reservation reservation); // 삽입 메서드 추가

    void update(Reservation reservation);

    void delete(int reservationId);

    List<Reservation> findByUserId(String userId);
}
