package com.lec.spring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long id;


    private String nickname;
    @JsonIgnore
    private String password;
    @ToString.Exclude
    @JsonIgnore
    private String re_password;
    @JsonIgnore
    private String username;

    private String email;

    @JsonIgnore
    private LocalDateTime regDate;

    private String phonenum;


}



