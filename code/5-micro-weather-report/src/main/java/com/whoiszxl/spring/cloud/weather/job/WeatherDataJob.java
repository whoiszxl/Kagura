package com.whoiszxl.spring.cloud.weather.job;

import com.whoiszxl.spring.cloud.weather.service.CityDataService;
import com.whoiszxl.spring.cloud.weather.service.WeatherDataService;
import com.whoiszxl.spring.cloud.weather.vo.City;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/1/31
 * \* Description: 天气定时时间处理任务
 * \
 */
public class WeatherDataJob extends QuartzJobBean {

    private final static Logger logger = LoggerFactory.getLogger(WeatherDataJob.class);

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("start quartz job.");

        //获取城市列表
        List<City> cityList = null;

        try {
            cityList = cityDataService.listCity();
        } catch (Exception e) {
            logger.error("获取XML城市列表错误", e);
        }

        //遍历城市id获取天气
        for (City city : cityList) {
            String id = city.getCityId();
            logger.info("weather job run....cityId is :"+id);
            weatherDataService.syncDateByCityId(id);
        }

        logger.info("end quartz job.");
    }
}