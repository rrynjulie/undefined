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

    public enum RoomSmoke {
        NON_SMOKING,
        SMOKING
    }


    public Room(String roomPicture1, String roomPicture2, String roomPicture3, String roomName, int roomNormalPeople, int roomMaxPeople,
                int price, String roomNumber, int roomArea, int roomBed, String roomBedGrade, String roomBathroom, RoomSmoke roomSmoke) {
        this.roomPicture1 = roomPicture1;
        this.roomPicture2 = roomPicture2;
        this.roomPicture3 = roomPicture3;
        this.roomName = roomName;
        this.roomNormalPeople = roomNormalPeople;
        this.roomMaxPeople = roomMaxPeople;
        this.price = price;
        this.roomNumber = roomNumber;
        this.roomArea = roomArea;
        this.roomBed = roomBed;
        this.roomBedGrade = roomBedGrade;
        this.roomBathroom = roomBathroom;
        this.roomSmoke = roomSmoke;
    }
}

