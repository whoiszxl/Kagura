package com.whoiszxl.spring.cloud.weather.service;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/2/1
 * \* Description:
 * \ 天气数据收集服务
 */
public interface WeatherDataCollectionService {

    /**
     * 通过cityId同步天气
     * @param cityId
     */
    void syncWeatherDataByCityId(String cityId);

}