package com.rmi.client.controller;

import com.rmi.client.model.Sample;
import com.rmi.client.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client/v1/execute")
public class SampleController {

    @Autowired
    private ApplicationContext context;

    @PostMapping
    public Sample execute() {
        SampleService sampleService = context.getBean(SampleService.class);
        return sampleService.execute("invoked by client");
    }

    @Bean
    private HessianProxyFactoryBean invoker() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        String serverUrl = "http://localhost:8081/exporter/sample";
        factory.setServiceUrl(serverUrl);
        factory.setServiceInterface(SampleService.class);
        return factory;
    }

}
