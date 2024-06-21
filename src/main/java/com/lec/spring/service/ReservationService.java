package com.lec.spring.service;

import com.lec.spring.domain.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> findAll();

    Reservation findById(int reservationId);

    void save(Reservation reservation);

    void delete(int reservationId);


}
