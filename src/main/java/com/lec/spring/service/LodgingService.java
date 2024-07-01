package com.lec.spring.service;


import com.lec.spring.domain.Lodging;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



public interface LodgingService {

    List<Lodging> getLodgingsByLocation(String location);

    List<Lodging> getLodgingsByLocationAndType(String location, String type);

    List<Lodging> findLodgingByPriceASC(String location);

    List<Lodging> findLodgingByPriceDESC(String location);

    List<Lodging> findLodgingIdASC(String location);

    List<Lodging> findLodgingIdASCByType(String location, String type);

    List<Lodging> findLodgingByLocationAndTypeAndPriceASC(String location, String type);

    List<Lodging> findLodgingByLocationAndTypeAndPriceDESC(String location, String type);

    List<Lodging> lodgingDetail(Long lodgingId);

    List<Lodging> lodgingName(Long lodgingId);

    List<Lodging> getPostList(Long lodgingId);

    Lodging getLodgingById(Long lodgingId);

    Double getAvgPostGrade(Long lodgingId);

    Integer getTotalPosts(Long lodgingId);

    List<Lodging> getCountHotelBookingWithRatings();
}
