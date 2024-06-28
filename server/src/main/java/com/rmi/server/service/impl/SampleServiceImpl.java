package com.rmi.server.service.impl;

import com.rmi.server.model.Sample;
import com.rmi.server.service.SampleService;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

    @Override
    public Sample execute(String param) throws RuntimeException {
        return new Sample(param);
    }

}
