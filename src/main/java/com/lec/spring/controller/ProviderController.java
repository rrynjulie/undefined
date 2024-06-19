package com.lec.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;



@Controller
public class ProviderController {
    // 파일 업로드 디렉토리 설정 (application.yml에 설정한 경로)
    // 파일 업로드 디렉토리 설정 (application.yml에 설정한 경로)
    @Value("${spring.upload.path}")
    private String uploadPath;

    @GetMapping("/lodgingregister")
    public String lodgingregister() {
        return "mypage/provider/ProvLodgingRegister";
    }

    @PostMapping("/uploadLodgingImages")
    @ResponseBody
    public ResponseEntity<String> uploadLodgingImages(@RequestParam("files") MultipartFile[] files) {
        return uploadImages(files, "숙소");
    }


    @GetMapping("/roomregister")
    public String roomregister() {
        return "mypage/provider/ProvRoomRegister";
    }


    @PostMapping("/uploadRoomImages")
    @ResponseBody
    public ResponseEntity<String> uploadRoomImages(@RequestParam("files") MultipartFile[] files) {
        return uploadImages(files, "객실");
    }

    private ResponseEntity<String> uploadImages(MultipartFile[] files, String imageType) {
        try {
            for (MultipartFile file : files) {
                // 원본 파일 이름 가져오기
                String originalFilename = file.getOriginalFilename();
                // 충돌을 피하기 위해 고유 파일 이름 생성
                String filename = UUID.randomUUID().toString() + "_" + originalFilename;

                // 업로드 디렉토리 경로 설정
                Path uploadDir = Paths.get(uploadPath);
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // 파일을 서버에 저장
                Path filePath = uploadDir.resolve(filename);
                file.transferTo(filePath);
            }

            // 업로드 성공 메시지 반환
            return ResponseEntity.ok(imageType + " 이미지 업로드 성공");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(imageType + " 이미지 업로드 실패");
        }
    }
}
