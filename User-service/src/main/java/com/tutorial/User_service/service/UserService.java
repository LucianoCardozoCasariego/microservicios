package com.tutorial.User_service.service;

import com.tutorial.User_service.dto.FullUserData;
import com.tutorial.User_service.entity.UserEntity;
import com.tutorial.User_service.feignClient.BikeFeignClient;
import com.tutorial.User_service.feignClient.CarFeignClient;
import com.tutorial.User_service.feignModels.BikeModel;
import com.tutorial.User_service.feignModels.CarModel;
import com.tutorial.User_service.repository.UserRepositoty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepositoty userRepositoty;
    @Autowired
    CarFeignClient carFeignClient;
    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<UserEntity> listAllUsers(){
        return this.userRepositoty.findAll();
    }

    public UserEntity getUserByID(Long userID){
        return this.userRepositoty.findById(userID).orElse(null);
    }

    public UserEntity createUser(UserEntity user){
        return this.userRepositoty.save(user);
    }

    public CarModel saveCar(Long userID,CarModel car){
        car.setUserID(userID);
        return this.carFeignClient.createCar(car);
    }

    public BikeModel saveBike(Long userID, BikeModel bike) {
        bike.setUserID(userID);
        return this.bikeFeignClient.createBikes(bike);
    }

    public FullUserData getFullUserData(Long userID) {
        UserEntity user = this.getUserByID(userID);
        List<CarModel> cars = this.carFeignClient.findByUserId(userID);
        List<BikeModel>  bikes = this.bikeFeignClient.findByUserId(userID);

       return new FullUserData(user, cars, bikes);
    }
}
