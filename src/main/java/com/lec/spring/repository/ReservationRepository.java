package com.lec.spring.repository;

import com.lec.spring.domain.Reservation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationRepository {
    List<Reservation> findAll();

    Reservation findById(int reservationId); //예약 Id

    void insert(Reservation reservation); //

    List<Reservation> findByUserId(String userId);

    @Delete("DELETE FROM reservation WHERE user_id = #{userId} AND reservation_id = #{reservationId}")
    int deleteByUserIdAndId(@Param("userId") String userId, @Param("reservationId") Long reservationId);


}
