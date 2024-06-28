package com.lec.spring.repository;



import com.lec.spring.domain.Lodging;


import java.util.List;


//@Mapper
public interface LodgingRepository {
    List<Lodging> findLodgingsByLocation(String location);

    List<Lodging> findLodgingsByLocationAndType(String location, String type);

    List<Lodging> findLodgingById(Long lodgingId);

    List<Lodging> findLodgingByName(Long lodgingId);

    List<Lodging> allPostList(Long lodgingId);

    Double avgPostGrade(Long lodgingId);

    Integer totalPosts(Long lodgingId);
}
