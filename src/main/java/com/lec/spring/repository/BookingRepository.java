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
//    @Delete("DELETE FROM booking WHERE user_id = #{userId} AND booking_id = #{bookingId}")
//    int deleteByUserIdAndId(@Param("userId") String userId, @Param("bookingId") Long bookingId);

    Booking save(Booking booking);

    Booking getBookingDetails(@Param("bookingId") Long bookingId);
}
