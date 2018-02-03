package com.whoiszxl.spring.cloud.weather.service.impl;


import com.whoiszxl.spring.cloud.weather.service.WeatherDataClient;
import com.whoiszxl.spring.cloud.weather.service.WeatherReportService;
import com.whoiszxl.spring.cloud.weather.vo.Forecast;
import com.whoiszxl.spring.cloud.weather.vo.Weather;
import com.whoiszxl.spring.cloud.weather.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataClient weatherDataClient;

    @Override
    public Weather getDataByCityId(String cityId) {

        WeatherResponse response = weatherDataClient.getDataByCityId(cityId);
        Weather data = response.getData();
        return data;
    }
}
