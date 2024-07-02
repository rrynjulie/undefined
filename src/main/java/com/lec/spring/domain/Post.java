package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private Long postId;  // NOT NULL
    private String postPicture;
    private String postText;  // NOT NULL
    private double postGrade;  // NOT NULL

    // Join 해서 가져온 컬럼
    private String lodgingName;
    private String lodgingPicture1;
    private String lodgingType;
    private String roomName;
    private String startDate;
    private String endDate;
    private String userName;
    private String userNickname;

    // Foreign Key
    private Long userId;  // NOT NULL
    private Long bookingId;  // NOT NULL
    private Long lodgingId;  // NOT NULL
    private Long roomId;  // NOT NULL
}