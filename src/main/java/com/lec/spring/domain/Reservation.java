package com.lec.spring.domain;

import lombok.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    private Integer reservationId; //예약 Id
    private Date reservationTime;
    private int reservationAdult; //성인예약인원
    private int reservationChild; //어린이예약인원
    private String visitorName; //예약자이름
    private String visitorPhoneNum; //예약자 전화번호
    private String reservationPayType; //결제방식
    private long reservationPay; //결제금액
    private long reservationFinalPay; //최종결제금액
    private Date reservationStartDate; //체크인 날짜
    private Date reservationEndDate; //체크아웃 날짜
    private Integer roomId;
    private String userId;
}

