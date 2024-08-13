package com.tutorial.Car_service.repository;

import com.tutorial.Car_service.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepositoty extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findAllByUserID(Long userID);
}
