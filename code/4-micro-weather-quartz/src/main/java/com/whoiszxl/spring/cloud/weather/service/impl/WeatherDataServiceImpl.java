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

    @Autowired
    private RestTemplate restTemplate;


    private final static Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);

    private static final long TIME_OUT = 1800L; // 1800s

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

    @Override
    public void syncDateByCityId(String cityId) {
        String uri = WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    /**
     * 把天气数据放在缓存
     *
     * @param uri
     */
    private void saveWeatherData(String uri) {
        String key = uri;
        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        // 调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }

        // 数据写入缓存
        ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

    }


    private WeatherResponse doGetWeather(String uri) {

        //设置redis的key
        String key = uri;
        //rest请求到的天气json串
        String strBody = null;

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
            //使用rest去第三方接口请求
            ResponseEntity<String> responseStr = restTemplate.getForEntity(uri, String.class);
            //如果请求的状态为200成功
            if (responseStr.getStatusCodeValue() == 200) {
                //获取到json主体
                strBody = responseStr.getBody();
            }

            ops.set(key, strBody, TIME_OUT, TimeUnit.SECONDS);

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