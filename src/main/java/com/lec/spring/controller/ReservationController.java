package com.lec.spring.controller;

import com.lec.spring.domain.Lodging;
import com.lec.spring.domain.Reservation;

import com.lec.spring.domain.Room;
import com.lec.spring.domain.User;
import com.lec.spring.service.LodgingService;
import com.lec.spring.service.ReservationService;
import com.lec.spring.service.RoomService;
import com.lec.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReservationController {
    private final ReservationService reservationService;
    private final LodgingService lodgingService;

    @Autowired
    public ReservationController(ReservationService reservationService, LodgingService lodgingService) {
        this.reservationService = reservationService;
        this.lodgingService = lodgingService;
    }

    // 숙소 예약 폼 페이지 보여주기
    @GetMapping("/lodging/reservation/form")
    public String showReservationForm(@RequestParam("lodgingId") Long lodgingId, Model model) {
        Lodging lodging = lodgingService.getLodgingById(lodgingId);
        model.addAttribute("lodging", lodging);
        model.addAttribute("reservation", new Reservation());
        return "lodging/LodgingBooking";
    }
}