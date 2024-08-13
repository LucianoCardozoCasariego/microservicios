package com.tutorial.Car_service.service;

import com.tutorial.Car_service.entity.CarEntity;
import com.tutorial.Car_service.repository.CarRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepositoty carRepositoty;

    public List<CarEntity> listAllCars() {
        return this.carRepositoty.findAll();
    }

    public CarEntity getCarByID(Long carID) {
        return this.carRepositoty.findById(carID).orElse(null);
    }

    public CarEntity createCar(CarEntity car) {
        return this.carRepositoty.save(car);
    }

    public List<CarEntity> getCarsByUserID(Long userID){
        return this.carRepositoty.findAllByUserID(userID);
    }
}
