package com.lec.spring.repository;

import com.lec.spring.domain.Booking;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BookingRepository {
    int save(Booking booking);

    Booking findByBookingId(Long bookingId);

    List<Booking> findBooksByRoomId(Long roomId);

    List<Booking> findBooksByUserId(Long userId);

    int deleteBooking(Booking booking);

    int bookingCount(Long roomId, LocalDate bookingStartDate, LocalDate bookingEndDate);
}