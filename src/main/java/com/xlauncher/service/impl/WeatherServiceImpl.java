package com.xlauncher.service.impl;

import com.xlauncher.service.WeatherService;

import javax.jws.WebService;

@WebService
public class WeatherServiceImpl implements WeatherService {

    public String queryWeather(String cityName) {
        System.out.println(cityName + "天气是：晴天");
        return "晴";
    }

}
