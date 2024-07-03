package com.lec.spring.repository;



import com.lec.spring.domain.Booking;
import com.lec.spring.domain.Lodging;


import java.util.List;


//@Mapper
public interface LodgingRepository {
    List<Lodging> findLodgingsByLocation(String location);

    List<Lodging> findLodgingsByLocationAndType(String location, String type);

    List<Lodging> findLodgingByPriceASC(String location);

    List<Lodging> findLodgingByPriceDESC(String location);

    List<Lodging> findLodgingIdASC(String location);

    List<Lodging> findLodgingIdASCByType(String location, String type);

    List<Lodging> findLodgingByLocationAndTypeAndPriceASC(String location, String type);

    List<Lodging> findLodgingByLocationAndTypeAndPriceDESC(String location, String type);

    List<Lodging> findLodgingById(Long lodgingId);

    List<Lodging> findLodgingByName(Long lodgingId);

    List<Lodging> allPostList(Long lodgingId);

    Double avgPostGrade(Long lodgingId);

    Integer totalPosts(Long lodgingId);

    List<Lodging> countHotelBookingWithRatings();

    List<Lodging> countHotelBookingWithRatingsByBookingCount();

    List<Lodging> countHotelBookingWithRatingsByLoveCount();
}
