package com.whoiszxl.spring.cloud.weather.controller;

import com.whoiszxl.spring.cloud.weather.service.WeatherReportService;
import com.whoiszxl.spring.cloud.weather.vo.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Administrator on 2018/2/1.
 */
@Controller
@RequestMapping("/report")
public class WeatherReportController {


    @Autowired
    private WeatherReportService weatherReportService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
        // todo 由城市api微服务提供数据
        List<City> cityList = null;

        cityList = new ArrayList<>();
        City city = new City();
        city.setCityId("101280800");
        city.setCityName("深圳");
        cityList.add(city);

        model.addAttribute("title", "w weather report.");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityList);
        model.addAttribute("report", weatherReportService.getDataByCityId(cityId));
        return new ModelAndView("weather/report", "reportModel", model);
    }

}
