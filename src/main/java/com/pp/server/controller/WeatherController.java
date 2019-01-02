package com.pp.server.controller;

import com.pp.server.entity.WeatherResponse;
import com.pp.server.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @RequestMapping(value = "/cityId/{cityId}", method = RequestMethod.GET)
    public WeatherResponse getReportByCityId(@PathVariable("cityId") String cityId)
    {
        return weatherService.getReportByCityId(cityId);
    }

    @RequestMapping(value = "/cityName/{cityName}", method = RequestMethod.GET)
    public WeatherResponse getReportByCityName(@PathVariable("cityName") String cityName)
    {
       return weatherService.getReportByCityName(cityName);
    }
}
