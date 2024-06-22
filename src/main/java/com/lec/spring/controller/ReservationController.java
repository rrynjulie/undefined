package com.lec.spring.controller;

import com.lec.spring.domain.Reservation;
import com.lec.spring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lodging")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약 생성 폼 페이지
    @GetMapping("/new")
    public String showCreateReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "lodging/LodgingBooking";
    }

    // 예약 생성
    @PostMapping
    public String createReservation(@ModelAttribute Reservation reservation, Model model) {
        reservationService.saveReservation(reservation);
        model.addAttribute("reservation", reservation);
        return "reservation/LodgingBookingOk";
    }


}
