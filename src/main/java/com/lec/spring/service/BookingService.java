package com.lec.spring.service;

import com.lec.spring.domain.Booking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    int createBooking(Booking booking);

    Booking findBookingByBookingId(Long bookingId);

    List<Booking> findBooksByRoomId(Long roomId);

    List<Booking> findBooksByUserId(Long userId);

    int deleteBooking(Long bookingId);

    LocalDate getBookingStartDate(Long bookingId);
}