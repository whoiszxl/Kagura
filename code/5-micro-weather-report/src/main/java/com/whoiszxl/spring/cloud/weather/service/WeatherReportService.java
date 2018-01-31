package com.whoiszxl.spring.cloud.weather.service;

import com.whoiszxl.spring.cloud.weather.vo.Weather;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/2/1.
 */
@Service
public interface WeatherReportService {

    /**
     * 根据城市ID查询天气信息
     * @param cityId
     * @return
     */
    Weather getDataByCityId(String cityId);

}
