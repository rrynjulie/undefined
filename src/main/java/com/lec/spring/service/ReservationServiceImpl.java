package com.lec.spring.service;

import com.lec.spring.domain.Reservation;
import com.lec.spring.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void saveReservation(Reservation reservation) {
        reservationRepository.insert(reservation); // insert 메서드 호출
    }

    @Override
    public List<Reservation> getReservationsByUserId(String userId) {
        return reservationRepository.findByUserId(userId);
    }

    @Override
    public int deleteReservationByUserIdAndId(String userId, Long reservationId) {
        return reservationRepository.deleteByUserIdAndId(userId, reservationId);
    }
}