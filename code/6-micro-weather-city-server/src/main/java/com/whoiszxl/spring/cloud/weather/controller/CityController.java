package com.whoiszxl.spring.cloud.weather.controller;

import com.whoiszxl.spring.cloud.weather.service.CityDataService;
import com.whoiszxl.spring.cloud.weather.vo.City;
import com.whoiszxl.spring.cloud.weather.vo.CityList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityDataService cityDataService;

    @RequestMapping("/list")
    public List<City> getCityList(){
        try {
            return cityDataService.listCity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
