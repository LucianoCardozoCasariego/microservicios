package com.tutorial.User_service.dto;

import com.tutorial.User_service.entity.UserEntity;
import com.tutorial.User_service.feignModels.BikeModel;
import com.tutorial.User_service.feignModels.CarModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullUserData {
    private UserEntity user;
    private List<CarModel> cars;
    private List<BikeModel> bikes;
}
