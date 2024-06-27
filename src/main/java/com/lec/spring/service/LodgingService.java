package com.lec.spring.service;


import com.lec.spring.domain.Lodging;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface LodgingService {

    List<Lodging> getLodgingsByLocation(String location);


    List<Lodging> lodgingDetail(Long lodgingId);

    List<Lodging> lodgingName(Long lodgingId);

    List<Lodging> getLodgingsByLocationAndType(String location, String type); // 새로운 메서드

    List<Lodging> getPostList(Long lodgingId);

    Lodging getLodgingById(Long lodgingId);

    Double getAvgPostGrade(Long lodgingId);
    Integer getTotalPosts(Long lodgingId);
}
