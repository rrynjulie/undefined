package com.lec.spring.controller;


import com.lec.spring.service.LodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lodging")
public class LodgingController {

//    @Autowired
//    private LodgingService lodgingService;


    @GetMapping("/LodgingSearch")
    public void LodgingSearch() {}

    @GetMapping("/LodgingList")
    public void LodgingList() {}

    @GetMapping("/LodgingDetail")
    public void LodgingDetail() {}

    @GetMapping("/RoomDetail")
    public void RoomDetail() {}




}
