package com.lec.spring.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 사용자와 권한 간의 관계
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerAuthority {
    private int userid; // 사용자 ID
    private int authorityId; // 권한 ID
}
