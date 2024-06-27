package com.lec.spring.domain;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private BookingPayType bookingPayType; //결제방식
    private int bookingPay; //결제금액
    private LocalDate bookingStartDate; //체크인 날짜
    private LocalDate bookingEndDate; //체크아웃 날짜
    private Long roomId; //객실 Id
    private Long userId; //사용자 ID

    private User user;
    private Room room;
    private ProvLodging lodging;

    private String formattedPay;
    private int dateGap;

    public enum BookingPayType {카드, 무통장입금}
}


