package com.tutorial.User_service.feignClient;

import com.tutorial.User_service.feignModels.CarModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "car-service", path = "/car")
public interface CarFeignClient {

    @PostMapping("")
    public CarModel createCar(@RequestBody CarModel carBody);

    @GetMapping("/byUser/{userID}")
    public List<CarModel> findByUserId(@PathVariable Long userID);
}
