package com.pp.server.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class RestConfiguration {

    @Autowired
    BeanFactory beanFactory;

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate()
    {
        return builder.build();
    }

    @Bean
    public ExecutorService executorService()
    {
        //sluth
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        return new TraceableExecutorService(this.beanFactory, executorService);
        //return builder.build();
    }




}
