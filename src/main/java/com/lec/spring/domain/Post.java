package com.lec.spring.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
    private Long postId;
    private String postText;
    private Double postGrade;
    private Long userId;
    private Long reservationId;
    private Long lodgingId;
    private Long roomId;
    private String postPicture;
}


