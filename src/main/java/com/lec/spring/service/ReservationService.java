package com.lec.spring.service;

import com.lec.spring.domain.Reservation;

import java.util.List;

public interface ReservationService {
//    List<Reservation> getReservationsByUserId(String userId);
//
//    int deleteReservationByUserIdAndId(String userId, Long reservationId);

    Reservation makeReservation(Reservation reservation);

    Reservation getReservationDetails(Long reservationId);

    Reservation save(Reservation reservation);



}
