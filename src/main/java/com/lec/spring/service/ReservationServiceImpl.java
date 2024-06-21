package com.lec.spring.service;

import com.lec.spring.domain.Reservation;
import com.lec.spring.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findById(int reservationId) {
        return reservationRepository.findById(reservationId);
    }

    @Override
    public void save(Reservation reservation) {
        if (reservation.getReservationId() == null) {
            reservationRepository.insert(reservation);
        } else {
            reservationRepository.update(reservation);
        }
    }

    @Override
    public void delete(int reservationId) {
        reservationRepository.delete(reservationId);
    }
}
