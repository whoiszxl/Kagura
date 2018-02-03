package com.whoiszxl.spring.cloud.weather.controller;

import com.whoiszxl.spring.cloud.weather.service.CityServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/2/3
 * \* Description:
 * \
 */
@RestController
public class CityController {


    @Autowired
    private CityServiceClient cityServiceClient;

    @GetMapping("/feign/cities")
    public String listCity(){
        return cityServiceClient.listCity();
    }


}