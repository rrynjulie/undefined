package com.lec.spring.repository;

import com.lec.spring.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationRepository {
    void insert(Reservation reservation);

    // 예약 ID로 예약 데이터 조회
    Reservation findById(int reservationId);

    // 모든 예약 데이터 조회
    List<Reservation> findAll();

    // 예약 데이터 업데이트
    void update(Reservation reservation);

    // 예약 ID로 예약 데이터 삭제
    void delete(int reservationId);
}
