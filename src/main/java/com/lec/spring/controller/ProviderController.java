package com.lec.spring.controller;

import com.lec.spring.domain.ProvLodging;
import com.lec.spring.domain.Room;
import com.lec.spring.service.ProviderService;
import com.lec.spring.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/mypage/provider")
public class ProviderController {
    // 파일 업로드 디렉토리 설정 (application.yml에 설정한 경로)
//    @Value("${spring.upload.path}")
//    private String uploadPath;

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

//    @PostMapping("/uploadLodgingImages")
//    @ResponseBody
//    public ResponseEntity<String> uploadLodgingImages(@RequestParam("files") MultipartFile[] files) {
//        return uploadImages(files, "숙소");
//    }

    @GetMapping("/provroomregister")
    public String provroomregister() {
        return "mypage/provider/ProvRoomRegister";
    }

//    @PostMapping("/uploadRoomImages")
//    @ResponseBody
//    public ResponseEntity<String> uploadRoomImages(@RequestParam("files") MultipartFile[] files) {
//        return uploadImages(files, "객실");
//    }
//
//    private ResponseEntity<String> uploadImages(MultipartFile[] files, String imageType) {
//        try {
//            for (MultipartFile file : files) {
//                // 원본 파일 이름 가져오기
//                String originalFilename = file.getOriginalFilename();
//                // 충돌을 피하기 위해 고유 파일 이름 생성
//                String filename = UUID.randomUUID().toString() + "_" + originalFilename;
//
//                // 업로드 디렉토리 경로 설정
//                Path uploadDir = Paths.get(uploadPath);
//                if (!Files.exists(uploadDir)) {
//                    Files.createDirectories(uploadDir);
//                }
//
//                // 파일을 서버에 저장
//                Path filePath = uploadDir.resolve(filename);
//                file.transferTo(filePath);
//            }
//
//            // 업로드 성공 메시지 반환
//            return ResponseEntity.ok(imageType + " 이미지 업로드 성공");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(imageType + " 이미지 업로드 실패");
//        }
//    }

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