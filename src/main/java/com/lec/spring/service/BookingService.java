package com.lec.spring.service;

import com.lec.spring.domain.Booking;

public interface BookingService {
    Booking makeBooking(Booking booking);

    Booking getBookingDetails(Long bookingId);

    Booking saveBooking(Booking booking);

}