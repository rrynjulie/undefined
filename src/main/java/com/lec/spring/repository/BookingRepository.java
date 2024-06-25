package com.lec.spring.repository;

import com.lec.spring.domain.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookingRepository {
    int createBooking(Booking booking);

    int deleteBooking(Booking booking);
}
