package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    private int reservationId;
    private LocalDateTime reservationTime;
    private int reservationAdult;
    private int reservationChild;
    private String visitorName;
    private String visitorPhonenum;
    private String reservationPaytype;
    private long reservationPay;
    private long reservationFinalpay;
    private LocalDateTime reservationStartdate;
    private LocalDateTime reservationEnddate;
    private int roomId;
    private String userId;
}
