package com.lec.spring.service;


import com.lec.spring.domain.Lodging;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface LodgingService {

    List<Lodging> getLodgingsByLocation(String location);


    List<Lodging> lodgingDetail(Long lodgingId);

    List<Lodging> lodgingName(Long lodgingId);

    List<Lodging> getLodgingsByType(String type);

    List<Lodging> getLodgingsByLocationAndType(String location, String type); // 새로운 메서드


}
