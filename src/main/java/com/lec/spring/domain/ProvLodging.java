package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProvLodging {
    private Long lodgingId;                 // 숙소 ID
    private String lodgingPicture1;        // 숙소 사진 1
    private String lodgingPicture2;        // 숙소 사진 2
    private String lodgingPicture3;        // 숙소 사진 3
    private String lodgingName;            // 숙소 이름
    private String lodgingType;            // 숙소 유형
    private String lodgingLocation1;       // 숙소 위치1
    private String lodgingLocation2;       // 숙소 위치2
    private String lodgingAddress;         // 숙소 주소
    private String lodgingNumber;          // 숙소 전화번호
    private String lodgingUrl;             // 숙소 URL
    private String lodgingOpen;            // 숙소 오픈 시간
    private String lodgingClose;           // 숙소 마감 시간
    private String lodgingIntroduce;       // 숙소 소개
    private String lodgingService;         // 숙소 서비스
    private String lodgingUsingInfo;       // 숙소 이용 정보
    private String lodgingNotice;          // 숙소 공지사항
    private String lodgingOwnerName;       // 숙소 주인 이름
    private String lodgingOwnerNumber;     // 숙소 주인 전화번호
    private String lodgingOwnerEmail;      // 숙소 주인 이메일
    private String lodgingOwnerId;         // 숙소 주인 ID
//    private boolean lodgingApproval;       // 숙소 승인 여부

    private Long userId;

    private List<Room> roomList;
}