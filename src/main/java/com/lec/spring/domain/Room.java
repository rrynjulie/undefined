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
    private String roomName;
    private int roomNormalPeople;
    private int roomMaxPeople;
    private int roomPrice;
    private String roomNumber;
    private String roomArea;
    private int roomBed;
    private String roomBedGrade;
    private int roomBathroom;
    private RoomSmoke roomSmoke;

    private Long lodgingId;

    private int available;

    public enum RoomSmoke {YES, NO}
}