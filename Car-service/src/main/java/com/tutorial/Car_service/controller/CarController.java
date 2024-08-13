package com.tutorial.Car_service.controller;

import com.tutorial.Car_service.entity.CarEntity;
import com.tutorial.Car_service.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    @Autowired
    CarService carService;

    @GetMapping("")
    public ResponseEntity<?> listAllCars(){
        List<CarEntity> lista =this.carService.listAllCars();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{carID}")
    public ResponseEntity<?> getCarsByID(@PathVariable Long carID){
        CarEntity car =this.carService.getCarByID(carID);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/byUser/{userID}")
    public ResponseEntity<?> findByUserId(@PathVariable Long userID){
        List<CarEntity> car =this.carService.getCarsByUserID(userID);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createCar(@RequestBody CarEntity carBody){
        CarEntity car =this.carService.createCar(carBody);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
