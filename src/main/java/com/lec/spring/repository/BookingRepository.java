package com.lec.spring.repository;

import com.lec.spring.domain.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookingRepository {
    int createBooking(Booking booking);

    List<Booking> findBooksByRoomId(Long roomId);

    List<Booking> findBooksByUserId(Long userId);

    int deleteBooking(Booking booking);
}
