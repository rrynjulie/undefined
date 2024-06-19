package com.lec.spring.controller;


import com.lec.spring.domain.Lodging;
import com.lec.spring.repository.LodgingRepository;
import com.lec.spring.service.LodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/lodging")
public class LodgingController {

//    private LodgingService lodgingService;
//
//    public LodgingController(LodgingService lodgingService) {
//        this.lodgingService = lodgingService;
//    }



    @GetMapping("/LodgingSearch")
    public void LodgingSearch() {}

    @GetMapping("/LodgingList")
    public void LodgingList(Model model) {
//        List<Lodging> lodgings = lodgingService.findAllLodging();
//        model.addAttribute("lodgings", lodgings);
    }

    @GetMapping("/LodgingDetail")
    public void LodgingDetail() {}

    @GetMapping("/RoomDetail")
    public void RoomDetail() {}




}
