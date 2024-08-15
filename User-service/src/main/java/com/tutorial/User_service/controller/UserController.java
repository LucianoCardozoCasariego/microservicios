package com.tutorial.User_service.controller;

import brave.Tracer;
import com.tutorial.User_service.dto.FullUserData;
import com.tutorial.User_service.entity.UserEntity;
import com.tutorial.User_service.feignModels.BikeModel;
import com.tutorial.User_service.feignModels.CarModel;
import com.tutorial.User_service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    private final Tracer tracer;

    public UserController(Tracer tracer) {
        this.tracer = tracer;
    }

    @GetMapping("")
    public ResponseEntity<?> listAllUsers() {
        List<UserEntity> lista = this.userService.listAllUsers();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{userID}")
    public ResponseEntity<?> getUserByID(@PathVariable Long userID) {
        UserEntity user = this.userService.getUserByID(userID);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CircuitBreaker(name = "allCB", fallbackMethod = "fallbackGetFullUser")
    @GetMapping("/{userID}/full")
    public ResponseEntity<?> getFullUser(@PathVariable Long userID) {
        String traceId = tracer.currentSpan().context().traceIdString();
        FullUserData user = this.userService.getFullUserData(userID);

        Map<String, Object> response = new HashMap<>();
        response.put("traceId", traceId);
        response.put("data", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createUser(@RequestBody UserEntity userBody) {
        UserEntity user = this.userService.createUser(userBody);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackCreateCar")
    @PostMapping("{userID}/createCar")
    public ResponseEntity<?> createCar(@PathVariable Long userID, @RequestBody CarModel car) {
        CarModel createdCar = this.userService.saveCar(userID, car);
        return new ResponseEntity<>(createdCar, HttpStatus.OK);
    }

    @CircuitBreaker(name = "bikesCB", fallbackMethod = "fallbackCreateBike")
    @PostMapping("{userID}/createBike")
    public ResponseEntity<?> createBike(@PathVariable Long userID, @RequestBody BikeModel bike) {
        BikeModel createdCar = this.userService.saveBike(userID, bike);
        return new ResponseEntity<>(createdCar, HttpStatus.OK);
    }

    private ResponseEntity<?> fallbackGetFullUser(@PathVariable Long userID, RuntimeException e) {
        return new ResponseEntity<>("aqui se mostrarian todos los vehiculos del usuario", HttpStatus.OK);
    }

    private ResponseEntity<?> fallbackCreateBike(@PathVariable Long userID, @RequestBody BikeModel bike, RuntimeException e) {
        return new ResponseEntity<>("No se pudo crear una bike", HttpStatus.OK);
    }

    private ResponseEntity<?> fallbackCreateCar(@PathVariable Long userID, @RequestBody CarModel car, RuntimeException e) {
        return new ResponseEntity<>("No se pudo crear un car", HttpStatus.OK);
    }

}
