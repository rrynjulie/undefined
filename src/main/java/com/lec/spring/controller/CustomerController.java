package com.lec.spring.controller;

import com.lec.spring.domain.Reservation;
import com.lec.spring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;


@Controller
@RequestMapping("mypage/customer")
public class CustomerController {

    private final ReservationService reservationService;

    @Autowired
    public CustomerController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 특정 사용자 ID로 예약 조회
    @GetMapping("/{userId}/reservation")
    public String getReservationsByUserId(@PathVariable String userId, Model model) {
        List<Reservation> reservations = reservationService.getReservationsByUserId(userId);
        model.addAttribute("reservations", reservations);
        return "mypage/customer/BookingList";
    }


}






