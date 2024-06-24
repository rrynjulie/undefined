package com.lec.spring.controller;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import com.lec.spring.service.ProviderService;
import com.lec.spring.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/mypage/provider")
public class ProviderController {


    @Autowired
    private ProviderService providerService;
    @Autowired
    private RoomService roomService;

    @Autowired
    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/provlodginglist")
    public String provlodginglist(Model model) {
        List<ProvLodging> lodgings = providerService.getAllLodgingDetails();
        model.addAttribute("lodgings", lodgings);
        return "mypage/provider/ProvLodgingList";
    }

    @GetMapping("/provlodgingdetail/{lodgingId}")
    public String provlodgingdetail(@PathVariable int lodgingId, Model model) {
        ProvLodging lodging = providerService.getLodgingById(lodgingId);
        model.addAttribute("lodging", lodging);
        return "mypage/provider/ProvLodgingDetail";
    }

    @GetMapping("/provlodgingregister")
    public String provlodgingregister() {
        return "mypage/provider/ProvLodgingRegister";
    }

    @GetMapping("/provroomregister")
    public String provroomregister() {
        return "mypage/provider/ProvRoomRegister";
    }


    @PostMapping("/saveLodging")
    public String saveLodging(@ModelAttribute ProvLodging lodging) {
        providerService.saveLodging(lodging);
        return "redirect:/provlodginglist";
    }

    @GetMapping("/ProvBookingList")
    public void provBookingList(Model model) {
    }

    @GetMapping("/ProvRoomRegister")
    public void provRoomRegister() {}

    @PostMapping("/ProvRoomRegister")
    public String provRoomRegisterOk() {
        return "provider/ProvRoomRegisterOk";
    }

    @GetMapping("/ProvRoomList/{userId}")
    public String provRoomList(@PathVariable("userId") Long userId, Model model) {
        List<ProvLodging> roomList = providerService.getLodgingsAndRoomsByUserId(userId);
        model.addAttribute("rooms", roomList);
        return "mypage/provider/ProvRoomList";
    }

    @GetMapping("/ProvRoomDetail/{roomId}")
    public String provRoomDetail(@PathVariable("roomId") Long roomId, Model model) {
        Room room = roomService.findByRoomId(roomId);
        model.addAttribute("room", room);
        return "mypage/provider/ProvRoomDetail";
    }

    @GetMapping("/ProvRoomUpdate/{roomId}")
    public String provRoomUpdate(@PathVariable Long roomId, Model model) {
        model.addAttribute("room", roomService.findByRoomId(roomId));
        return "provider/ProvRoomUpdate";
    }

    @PostMapping("/ProvRoomUpdate")
    public String provRoomUpdateOk() {
        return "provider/ProvRoomUpdateOk";
    }
}