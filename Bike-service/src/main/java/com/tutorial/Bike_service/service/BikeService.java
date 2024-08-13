package com.tutorial.Bike_service.service;

import com.tutorial.Bike_service.entity.BikeEntity;
import com.tutorial.Bike_service.repository.BikeRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {
    @Autowired
    BikeRepositoty bikeRepositoty;

    public List<BikeEntity> listAllCars() {
        return this.bikeRepositoty.findAll();
    }

    public BikeEntity getCarByID(Long carID) {
        return this.bikeRepositoty.findById(carID).orElse(null);
    }

    public BikeEntity createCar(BikeEntity car) {
        return this.bikeRepositoty.save(car);
    }

    public List<BikeEntity> getCarsByUserID(Long userID){
        return this.bikeRepositoty.findAllByUserID(userID);
    }
}
