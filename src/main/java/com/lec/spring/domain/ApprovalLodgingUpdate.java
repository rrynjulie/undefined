package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApprovalLodgingUpdate {

    private Long lodgingId;             //숙소고유 id값
    private String lodging_approval;    //마스터의 숙소 승인 여부
    private String lodgingName;         //숙소 이름
    private String lodgingType;         //숙소 타입
    private String lodgingPicture1;     //숙소 사진1
    private int roomPrice;              //숙소 가격
    private String lodgingOpen;         //숙소 입실시간
    private String lodgingClose;        //숙소 퇴실시간
    private String lodgingIntroduce;    //숙소 소개
    private String lodgingService;      //숙소 시설 및 서비스
    private String lodgingUsingInfo;    //숙소 이용 안내
    private String lodgingNotice;       //숙소 예약 공지
    private String lodgingAddress;      //숙소 주소
    private String lodgingOwnerName;    //숙소 담당자 이름
    private String lodgingOwnerNumber;  //숙소 담당자 전화번호
    private String lodgingOwnerEmail;   //숙소 담당자 이메일
    private String lodgingOwnerId;      //숙소 담당자 사업자 등록 번호


}
