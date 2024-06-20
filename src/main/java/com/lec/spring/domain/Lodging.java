package com.lec.spring.domain;

import lombok.Getter;

public class Lodging {

    private Long lodgingId;
    private String lodgingLocation1;
    private String lodgingName;
    private byte lodgingPicture1;



    // 생성자, 게터세터
    public Lodging() {}

    public Lodging(String lodgingLocation1,String lodgingName) {
        this.lodgingLocation1 = lodgingLocation1;
        this.lodgingName = lodgingName;
    }
    public String getLodgingName() {
        return lodgingName;
    }
    public void setLodgingName(String lodgingName) {
        this.lodgingName = lodgingName;
    }

    public String getLodgingLocation1() {
        return lodgingLocation1;
    }
    public void setLodgingLocation1(String lodgingLocation1) {
        this.lodgingLocation1 = lodgingLocation1;
    }



}