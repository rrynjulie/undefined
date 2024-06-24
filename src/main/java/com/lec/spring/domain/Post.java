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
    private Integer postId;
    private Integer postGrade;
    private String postText;
    private Integer userId;
    private Integer reservationId;
    private String postPicture;
}

