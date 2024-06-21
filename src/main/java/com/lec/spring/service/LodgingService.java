package com.lec.spring.service;


import com.lec.spring.domain.Lodging;
import java.util.List;



public interface LodgingService {

//    List<Lodging> getAllLodgingDetails();
    List<Lodging> getLodgingsByLocation(String location);

}
