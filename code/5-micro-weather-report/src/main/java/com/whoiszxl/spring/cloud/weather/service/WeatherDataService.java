package com.whoiszxl.spring.cloud.weather.service;

import com.whoiszxl.spring.cloud.weather.vo.WeatherResponse;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/1/31
 * \* Description:
 * \
 */
public interface WeatherDataService {

    /**
     * 通过城市id查询天气信息
     *
     * @param cityId
     * @return
     */
    WeatherResponse getDataByCityId(String cityId);


    /**
     * 根据城市名称查询天气数据
     *
     * @param cityName
     * @return
     */
    WeatherResponse getDataByCityName(String cityName);


    /**
     * 根据城市ID来同步天气
     *
     * @param cityId
     */
    void syncDateByCityId(String cityId);

}