package com.xlauncher.pubish;

import com.xlauncher.service.impl.WeatherServiceImpl;

import javax.xml.ws.Endpoint;

public class ServerPoint {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:12345/weather", new WeatherServiceImpl());
        System.out.println("发布成功");
    }
}
