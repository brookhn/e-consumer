package com.pp.server.controller;

import com.pp.server.entity.WeatherResponse;
import com.pp.server.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    Logger log = LoggerFactory.getLogger(WeatherController.class);

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ExecutorService executorService;


    @RequestMapping(value = "/shlo", method = RequestMethod.GET)
    public String helloSer()
    {
        log.info("client sent ,子线程方式， 参数{}");
        String responseF = null;
        Future future = executorService.submit(()->{
            String response = weatherService.helloSer();
            return response;
        });
        try {
            responseF = (String)(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return responseF;
    }

    @RequestMapping(value = "/cityId/{cityId}", method = RequestMethod.GET)
    public WeatherResponse getReportByCityId(@PathVariable("cityId") String cityId)
    {
        return weatherService.getReportByCityId(cityId);
    }

    @RequestMapping(value = "/cityName/{cityName}", method = RequestMethod.GET)
    public WeatherResponse getReportByCityName(@PathVariable("cityName") String cityName)
    {
        log.info("client sent ,子线程方式， 参数{}", cityName);
        WeatherResponse responseF = null;
        Future future = executorService.submit(()->{
            WeatherResponse response = weatherService.getReportByCityName(cityName);
            return response;
        });
        try {
            responseF = (WeatherResponse)(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return responseF;
    }
}
