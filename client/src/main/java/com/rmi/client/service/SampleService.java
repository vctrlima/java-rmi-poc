package com.rmi.client.service;

import com.rmi.client.model.Sample;

public interface SampleService {
    Sample execute(String param) throws RuntimeException;
}
