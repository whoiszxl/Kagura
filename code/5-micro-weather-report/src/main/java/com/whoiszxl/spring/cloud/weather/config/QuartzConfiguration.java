package com.whoiszxl.spring.cloud.weather.config;

import com.whoiszxl.spring.cloud.weather.job.WeatherDataJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: whoiszxl
 * \* Date: 2018/1/31
 * \* Description:
 * \
 */
@Configuration
public class QuartzConfiguration {

    private static final int TIME = 1800; // 更新频率

    @Bean
    public JobDetail weatherDataJobDetail(){
        return JobBuilder.newJob(WeatherDataJob.class).withIdentity("weatherDataJob").storeDurably().build();
    }

    @Bean
    public Trigger weatherDataTrigger(){

        //时间只会读取一次,动态的更改时间是没用的
        //ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //int t = Integer.parseInt(ops.get("time"));

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(TIME).repeatForever();

        return TriggerBuilder.newTrigger().forJob(weatherDataJobDetail()).withIdentity("weatherDataTrigger").withSchedule(scheduleBuilder).build();
    }

}