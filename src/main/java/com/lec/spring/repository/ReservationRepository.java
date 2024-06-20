package com.lec.spring.repository;

import com.lec.spring.domain.Reservation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReservationRepository {
    Reservation findById(@Param("reservationId") int reservationId);

    List<Reservation> findByUserId(@Param("userId") int userId);

    List<Reservation> findAll();

    void insert(Reservation reservation);

    void update(Reservation reservation);

//    void delete(@Param("reservationId") int reservationId);


}
