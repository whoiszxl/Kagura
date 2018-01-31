package com.whoiszxl.spring.cloud.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whoiszxl.spring.cloud.weather.service.WeatherDataService;
import com.whoiszxl.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/1/31
 * \* Description: 获取天气信息的服务类
 * \
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI + "city=" + cityName;
        return this.doGetWeather(uri);
    }


    private WeatherResponse doGetWeather(String uri) {
        ResponseEntity<String> responseStr = restTemplate.getForEntity(uri, String.class);

        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse weatherResponse = null;
        String strBody = null;

        if (responseStr.getStatusCodeValue() == 200) {
            strBody = responseStr.getBody();
        }

        try {
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return weatherResponse;

    }
}