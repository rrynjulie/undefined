package com.lec.spring.controller;

import com.lec.spring.domain.Reservation;
import com.lec.spring.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // 특정 사용자 ID의 예약 삭제 처리
    @PostMapping("/{userId}/reservation/delete/{reservationId}")
    public String deleteReservationByUserId(@PathVariable String userId, @PathVariable Long reservationId, Model model) {
        int deleteCount = reservationService.deleteReservationByUserIdAndId(userId, reservationId);
        if (deleteCount > 0) {
            model.addAttribute("result", "삭제 성공");
        } else {
            model.addAttribute("result", "삭제 실패");
        }
        // 삭제 후 예약 목록 페이지로 리다이렉트
        return "redirect:/mypage/customer/{userId}/reservation";
    }




}






