package com.lec.spring.service;

import com.lec.spring.domain.Booking;
import com.lec.spring.domain.ProvLodging;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    int createBooking(Booking booking);

    List<Booking> findBooksByRoomId(Long roomId);

    List<Booking> findBooksByUserId(Long userId);

    int deleteByBookingId(Long bookingId);

    LocalDate getBookingStartDate(Long bookingId);
}