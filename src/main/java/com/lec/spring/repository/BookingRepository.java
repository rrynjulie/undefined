package com.lec.spring.repository;

import com.lec.spring.domain.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookingRepository {
//    List<Reservation> findAll();
//
//    List<Reservation> findByUserId(String userId);
//
//    @Delete("DELETE FROM reservation WHERE user_id = #{userId} AND reservation_id = #{reservationId}")
//    int deleteByUserIdAndId(@Param("userId") String userId, @Param("reservationId") Long reservationId);

    Booking save(Booking booking);

    Booking getReservationDetails(@Param("bookingId") Long bookingId);
}
