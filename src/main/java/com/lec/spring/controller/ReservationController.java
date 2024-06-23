package com.lec.spring.controller;

import com.lec.spring.domain.Lodging;
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

    // 예약 폼을 보여주는 메소드
    @GetMapping("/form")
    public String showReservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "lodging/LodgingBooking";
    }

    // 예약을 제출하는 메소드
    @PostMapping("/submit")
    public String submitReservation(@ModelAttribute("reservation") Reservation reservation, Model model) {
        Reservation savedReservation = reservationService.makeReservation(reservation);

        // lodging, room, user 정보를 reservation 객체에서 가져와서 model에 추가
        model.addAttribute("lodging", savedReservation.getLodging());
        model.addAttribute("room", savedReservation.getRoom());
        model.addAttribute("user", savedReservation.getUser());

        // 예약 상세 페이지로 redirect
        return "redirect:/lodging/details/" + savedReservation.getReservationId();
    }

    // 예약 상세 정보를 보여주는 메소드
    @GetMapping("/details/{reservationId}")
    public String showReservationDetails(@PathVariable Long reservationId, Model model) {
        Reservation reservation = reservationService.getReservationDetails(reservationId);
        model.addAttribute("reservation", reservation);
        return "reservation-details";
    }
}
