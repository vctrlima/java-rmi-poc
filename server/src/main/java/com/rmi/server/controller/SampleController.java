package com.rmi.server.controller;

import com.rmi.server.model.Sample;
import com.rmi.server.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server/v1/execute")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @PostMapping
    public Sample execute() {
        return sampleService.execute("invoked by server");
    }

}
