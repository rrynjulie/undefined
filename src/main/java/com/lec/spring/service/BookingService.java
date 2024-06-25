package com.lec.spring.service;

import com.lec.spring.domain.Booking;

public interface BookingService {
//    List<Booking> getBookingsByUserId(String userId);
//
//    int deleteBookingByUserIdAndId(String userId, Long bookingId);

    Booking makeBooking(Booking booking);

    Booking getBookingDetails(Long bookingId);

    Booking saveBooking(Booking booking);



}
