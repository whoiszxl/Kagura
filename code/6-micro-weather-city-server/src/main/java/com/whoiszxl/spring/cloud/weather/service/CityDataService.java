package com.whoiszxl.spring.cloud.weather.service;

import com.whoiszxl.spring.cloud.weather.vo.City;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/1/31
 * \* Description:
 * \
 */
public interface CityDataService {

    /**
     * 获取City列表
     * @return
     * @throws Exception
     */
    List<City> listCity() throws Exception;
}