package com.lec.spring.service;

import com.lec.spring.domain.Reservation;

import java.util.List;

public interface ReservationService {

    void saveReservation(Reservation reservation);
    List<Reservation> getReservationsByUserId(String userId);

    int deleteReservationByUserIdAndId(String userId, Long reservationId);



}
