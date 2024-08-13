package com.tutorial.Bike_service.repository;

import com.tutorial.Bike_service.entity.BikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeRepositoty extends JpaRepository<BikeEntity, Long> {

    List<BikeEntity> findAllByUserID(Long userID);
}
