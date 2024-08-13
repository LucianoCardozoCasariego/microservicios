package com.tutorial.Bike_service.controller;

import com.tutorial.Bike_service.entity.BikeEntity;
import com.tutorial.Bike_service.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {
    @Autowired
    BikeService BikeService;

    @GetMapping("")
    public ResponseEntity<?> listAllBikes(){
        List<BikeEntity> lista =this.BikeService.listAllCars();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{bikeID}")
    public ResponseEntity<?> getBikesByID(@PathVariable Long bikeID){
        BikeEntity bike =this.BikeService.getCarByID(bikeID);
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    @GetMapping("/byUser/{userID}")
    public ResponseEntity<?> findByUserId(@PathVariable Long userID){
        List<BikeEntity> bike =this.BikeService.getCarsByUserID(userID);
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createBikes(@RequestBody BikeEntity bikeBody){
        BikeEntity bike =this.BikeService.createCar(bikeBody);
        return new ResponseEntity<>(bike, HttpStatus.OK);
    }
}
