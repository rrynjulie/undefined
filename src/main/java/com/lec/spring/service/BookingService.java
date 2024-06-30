package com.lec.spring.service;

import com.lec.spring.domain.Booking;
import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Room;
import com.lec.spring.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    int createBooking(Booking booking);

    List<Booking> findBooksByRoomId(Long roomId);

    List<Booking> findBooksByUserId(Long userId);

    int deleteBooking(Long bookingId);

    int bookingCount(Long roomId, LocalDate bookingStartDate, LocalDate bookingEndDate);
}