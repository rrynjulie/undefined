package com.lec.spring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Love {

    // 숙소 고유 id
    private Long lodgingId;

    // 좋아요를 누른 유저 id
    private Long userId;

    public Long getLodgingId() { return lodgingId; }

    public void setLodgingId(Long lodgingId) { this.lodgingId = lodgingId; }

    public Long getUserId() { return getUserId(); }

    public void setUserId(Long userId) { this.userId = userId; }


}
