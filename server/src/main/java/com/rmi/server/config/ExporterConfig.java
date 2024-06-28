package com.rmi.server.config;

import com.rmi.server.exporter.SampleExporter;
import com.rmi.server.service.SampleService;
import com.rmi.server.service.impl.SampleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.support.RemoteExporter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class ExporterConfig {

    @Autowired
    private SampleServiceImpl sampleService;

    @Bean(name = "/exporter/sample")
    RemoteExporter exporter() {
        SampleExporter exporter = new SampleExporter();
        exporter.setService(sampleService);
        exporter.setServiceInterface(SampleService.class);
        return exporter;
    }

}
