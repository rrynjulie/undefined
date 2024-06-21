package com.lec.spring.controller;


import com.lec.spring.service.LodgingService;
import com.lec.spring.domain.Lodging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/lodging")
public class LodgingController {

    private final LodgingService lodgingService;

    @Autowired
    public LodgingController(LodgingService lodgingService) {
        this.lodgingService = lodgingService;
    }

    @GetMapping("/LodgingSearch")
    public String showLodgingSearch() {
        return "lodging/LodgingSearch";
    }

    @PostMapping("/LodgingSearch")
    public String handleSearchRequest(@RequestParam("location") String location, Model model) {
        List<Lodging> lodgings = lodgingService.getLodgingsByLocation(location);
        model.addAttribute("lodgings", lodgings);
        return "lodging/LodgingList";
    }

    @GetMapping("/LodgingList")
    public void showLodgingListPage() {
//        return "LodgingList";
    }




//    @GetMapping("/LodgingList")
//    public void showLodgings(Model model) {
//        List<Lodging> lodgingList = lodgingService.getAllLodgingDetails();
//        model.addAttribute("lodgingList", lodgingList);
//
//    //    return "LodgingList";
//    }

    @GetMapping("/RoomDetail")
    public void RoomDetail() {}




}
