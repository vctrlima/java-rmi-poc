package com.rmi.server.service;

import com.rmi.server.model.Sample;

public interface SampleService {
    Sample execute(String param) throws RuntimeException;
}
