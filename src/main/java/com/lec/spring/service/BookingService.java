package com.lec.spring.service;

import com.lec.spring.domain.Booking;

public interface BookingService {
//    List<Reservation> getReservationsByUserId(String userId);
//
//    int deleteReservationByUserIdAndId(String userId, Long reservationId);

    Booking makeBooking(Booking booking);

    Booking getBookingDetails(Long bookingId);

    Booking saveBooking(Booking booking);



}
