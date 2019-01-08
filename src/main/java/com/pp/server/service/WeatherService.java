package com.pp.server.service;

import com.pp.server.entity.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "client.service")
public interface WeatherService {

    @RequestMapping("/weather/shlo")
    public String helloSer();

    @RequestMapping(value = "/weather/cityId/{cityId}", method = RequestMethod.GET)
    public WeatherResponse getReportByCityId(@PathVariable("cityId") String cityId);

    @RequestMapping(value = "/weather/cityName/{cityName}", method = RequestMethod.GET)
    public WeatherResponse getReportByCityName(@PathVariable("cityName") String cityName);
}
