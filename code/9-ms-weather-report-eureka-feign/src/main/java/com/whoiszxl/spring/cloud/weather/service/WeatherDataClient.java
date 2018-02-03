package com.whoiszxl.spring.cloud.weather.service;

import com.whoiszxl.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/2/3
 * \* Description:
 * \
 */
@FeignClient("ms-weather-data-eureka")
public interface WeatherDataClient {

    @GetMapping("/weather/city_id/{cityId}")
    WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);
}
