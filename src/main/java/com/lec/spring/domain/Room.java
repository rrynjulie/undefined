package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
    private Long roomId;
    private String roomPicture1;
    private String roomPicture2;
    private String roomPicture3;
    private String roomName;
    private int roomNormalPeople;
    private int roomMaxPeople;
    private int price;
    private String roomNumber;
    private int roomArea;
    private int roomBed;
    private String roomBedGrade;
    private String roomBathroom;
    private RoomSmoke roomSmoke;

    private Long lodgingId;

    public enum RoomSmoke {YES, NO}
}