package com.lec.spring.controller;


import com.lec.spring.service.LodgingService;
import com.lec.spring.domain.Lodging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lodging")
public class LodgingController {

    @Autowired
    private LodgingService lodgingService;

    @GetMapping("/LodgingSearch")
    public void LodgingSearch() {}


    @GetMapping("/LodgingList")
    public void getAllLodgingDetails(Model model) {
        List<Lodging> lodgingDetails = lodgingService.getAllLodgingDetails();
        model.addAttribute("lodgingDetails", lodgingDetails);

    //    return "LodgingList";
    }

    @GetMapping("/RoomDetail")
    public void RoomDetail() {}




}
