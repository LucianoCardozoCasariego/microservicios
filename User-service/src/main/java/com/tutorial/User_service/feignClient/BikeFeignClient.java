package com.tutorial.User_service.feignClient;

import com.tutorial.User_service.feignModels.BikeModel;
import com.tutorial.User_service.feignModels.CarModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service",path = "/bike")
public interface BikeFeignClient {

    @PostMapping("")
    public BikeModel createBikes(@RequestBody BikeModel bikeBody);

    @GetMapping("/byUser/{userID}")
    public List<BikeModel> findByUserId(@PathVariable Long userID);
}
