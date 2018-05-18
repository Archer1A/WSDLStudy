package com.xlauncher.service;

import javax.jws.WebMethod;

public interface WeatherService {

    @WebMethod
    String queryWeather(String cityName);
}
