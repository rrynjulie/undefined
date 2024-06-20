package com.lec.spring.service;

import com.lec.spring.domain.Reservation;
import com.lec.spring.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService{
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        reservationRepository.insert(reservation);
        return reservation;
    }

    @Override
    public Reservation getReservationById(int id) {
        return reservationRepository.findById(id);
    }

    @Override
    public List<Reservation> getReservationsByUserId(int userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        reservationRepository.update(reservation);
        return reservationRepository.findById(reservation.getReservationId());
    }

//    @Override
//    public boolean deleteReservation(int id) {
//        reservationRepository.delete(id);
//        return reservationRepository.findById(id) == null;
//    }

}
