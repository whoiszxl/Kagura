package com.whoiszxl.spring.cloud.weather.service;

import com.whoiszxl.spring.cloud.weather.vo.City;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/2/3
 * \* Description:
 * \
 */
@FeignClient("ms-weather-city-eureka")
public interface CityClient {

    @GetMapping("/city/list")
    List<City> listCity() throws Exception;
}
