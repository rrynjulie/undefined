package com.lec.spring.service;

import com.lec.spring.domain.Reservation;

import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation getReservationById(int id);

    List<Reservation> getReservationsByUserId(int userId);

    List<Reservation> getAllReservations();

    Reservation updateReservation(Reservation reservation);

//    boolean deleteReservation(int id);
}
