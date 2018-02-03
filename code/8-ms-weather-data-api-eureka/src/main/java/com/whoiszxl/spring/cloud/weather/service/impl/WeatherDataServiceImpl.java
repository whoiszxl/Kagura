package com.whoiszxl.spring.cloud.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.whoiszxl.spring.cloud.weather.service.WeatherDataService;
import com.whoiszxl.spring.cloud.weather.vo.WeatherResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


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

        //设置redis的key
        String key = uri;
        //rest请求到的天气json串
        String strBody;

        //对象映射转换工具
        ObjectMapper mapper = new ObjectMapper();
        //将要返回的前台对象
        WeatherResponse weatherResponse = null;


        //redis 操作字符串
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        //先查缓存,缓存不在就调接口,判断key是否在redis是否存在
        if (stringRedisTemplate.hasKey(key)) {
            //直接从redis中获取值保存
            logger.info("redis has data");
            strBody = ops.get(key);
        } else {
            //没有缓存
            logger.info("redis has not data");
            throw new RuntimeException("redis has not the weather info.");

        }
        try {
            // 将json信息映射到对象中
            weatherResponse = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            logger.error("json to object error!", e);
        }

        return weatherResponse;

    }
}