package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Long bookingId; //예약 Id
    private LocalDateTime bookingTime; //예약한 시간
    private int bookingAdult; //성인예약인원
    private int bookingChild; //어린이예약인원
    private String visitorName; //예약자이름
    private String visitorPhoneNum; //예약자 전화번호
    private String bookingPayType; //결제방식
    private long bookingPay; //결제금액
    private long bookingFinalPay; //최종결제금액
    private Date bookingStartDate; //체크인 날짜
    private Date bookingEndDate; //체크아웃 날짜
    private Integer roomId; //객실 Id
    private String userId; //사용자 ID

    private Room room;
    private User user;
    private Lodging lodging;
}

