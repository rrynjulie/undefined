package com.lec.spring.domain;

import lombok.*;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lodging {

    private Long lodgingId;
    private String lodgingName;
    private String lodgingType;
    private String lodgingPicture1;
    private String lodgingOpen;
    private String lodgingClose;
    private String lodgingIntroduce;
    private String lodgingService;
    private String lodgingUsingInfo;
    private String lodgingNotice;
    private String lodgingAddress;
    private String lodgingOwnerName;
    private String lodgingOwnerNumber;
    private String lodgingOwnerEmail;
    private String lodgingOwnerId;

    private Long roomId;
    private String roomName;
    private String roomPicture1;
    private String roomPicture2;
    private int available;
    private int roomPrice;  // 객실 최솟값
    private int roomPrices; // 한 숙소의 객실들 가격

    private String postPicture;
    private String postText;

    // 후기 평균값
    private double postGrade;

    //평점 평균을 저장하는 double타입
    private double avgPostGrade;
    // 총 평균을 저장하는 interger타입
    private Integer totalPosts;
    // 평점 갯수
    private int countPostGrade;

    private List<Booking> bookingList;

    // 좋아요 누른 여부
    private Boolean hasLove;

    private int bookingCount;

    private int loveCount;


    public String getFormattedRoomPrice() {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        return numberFormat.format(roomPrice);
    }


    public String getFormattedRoomPrices() {
        NumberFormat numberFormats = NumberFormat.getInstance(Locale.KOREA);
        return numberFormats.format(roomPrices);
    }

    public String getFormattedAvgPostGrade() {
        return String.format("%.1f", avgPostGrade);
    }

}