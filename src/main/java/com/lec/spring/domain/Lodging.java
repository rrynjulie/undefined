package com.lec.spring.domain;

import lombok.Getter;
import lombok.Setter;

import java.text.NumberFormat;
import java.util.Locale;

@Getter
@Setter
public class Lodging {

    private String lodgingName;
    private String lodgingPicture1;
    private int roomPrice;

    public String getFormattedRoomPrice() {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.KOREA);
        return numberFormat.format(roomPrice);
    }
}