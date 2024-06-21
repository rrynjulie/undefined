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

    //모든 예약 목록을 조회
    @GetMapping
    public String list(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "BookingList";
    }


    //특정 예약 ID에 해당하는 예약정보 조회하기
//    @GetMapping("/{id}")
//    public String view(@PathVariable("id") int reservationId, Model model) {
//        model.addAttribute("reservation", reservationService.findById(reservationId));
//        return "BookingList";
//    }

    //예약하기위한 Form
    @GetMapping("/booking")
    public String showBookingForm(Model model) {
        model.addAttribute("reservations", new Reservation());
        return "lodging/LodgingBooking";
    }

    //제출된 예약정보를 받아서 DB에 저장하고, 목록 페이지로 리다이렉트
//    @PostMapping("/add")
//    public String add(@ModelAttribute Reservation reservation) {
//        reservationService.save(reservation);
//        return "LodgingBookingOk";
//    }

    //특정 예약Id에 해당하는 예약정보를 받아서 수정하기위한 입력Form
//    @GetMapping("/edit/{id}")
//    public String editForm(@PathVariable("id") int reservationId, Model model) {
//        model.addAttribute("reservation", reservationService.findById(reservationId));
//        return "reservations/edit";
//    }

    //제출된 수정Form 예약정보를 받아서 DB에 업데이트
//    @PostMapping("/edit")
//    public String edit(@ModelAttribute Reservation reservation) {
//        reservationService.save(reservation);
//        return "redirect:/reservations";
//    }

    //특정 예약 Id에 해당하는 예약정보 삭제
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") int reservationId) {
//        reservationService.delete(reservationId);
//        return "redirect:/reservations";
//    }


}
