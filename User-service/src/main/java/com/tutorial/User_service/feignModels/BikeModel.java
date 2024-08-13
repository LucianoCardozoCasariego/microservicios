package com.tutorial.User_service.feignModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BikeModel {
    private String brand;
    private String model;
    private Long userID;
}

