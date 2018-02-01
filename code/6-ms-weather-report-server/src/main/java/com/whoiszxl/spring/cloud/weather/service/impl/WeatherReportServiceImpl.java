package com.whoiszxl.spring.cloud.weather.service.impl;


import com.whoiszxl.spring.cloud.weather.service.WeatherReportService;
import com.whoiszxl.spring.cloud.weather.vo.Forecast;
import com.whoiszxl.spring.cloud.weather.vo.Weather;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {


    @Override
    public Weather getDataByCityId(String cityId) {
        //TODO 改为天气数据API服务来提供
        Weather data = new Weather();
        data.setCity("深圳");
        data.setAqi("21");
        data.setGanmao("容易感冒");
        data.setWendu("23");
        List<Forecast> forecastList = new ArrayList<>();

        Forecast forecast = new Forecast();
        forecast.setDate("20日周二");
        forecast.setType("阴天");
        forecast.setFengli("大风");
        forecast.setHigh("最高温30度");
        forecast.setLow("最低温1度");
        forecastList.add(forecast);

        forecast = new Forecast();
        forecast.setDate("20日周二");
        forecast.setType("阴天");
        forecast.setFengli("大风");
        forecast.setHigh("最高温30度");
        forecast.setLow("最低温1度");
        forecastList.add(forecast);

        forecast = new Forecast();
        forecast.setDate("20日周二");
        forecast.setType("阴天");
        forecast.setFengli("大风");
        forecast.setHigh("最高温30度");
        forecast.setLow("最低温1度");
        forecastList.add(forecast);

        forecast = new Forecast();
        forecast.setDate("20日周二");
        forecast.setType("阴天");
        forecast.setFengli("大风");
        forecast.setHigh("最高温30度");
        forecast.setLow("最低温1度");
        forecastList.add(forecast);

        forecast = new Forecast();
        forecast.setDate("20日周二");
        forecast.setType("阴天");
        forecast.setFengli("大风");
        forecast.setHigh("最高温30度");
        forecast.setLow("最低温1度");
        forecastList.add(forecast);

        data.setForecast(forecastList);

        return data;
    }
}
