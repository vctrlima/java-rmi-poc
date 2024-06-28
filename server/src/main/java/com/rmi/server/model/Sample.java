package com.rmi.server.model;

import lombok.Data;

import java.util.Date;

@Data
public class Sample {

    String param;
    String actuator;
    Date queuedAt;

    public Sample(String param) {
        this.param = param;
        this.actuator = "com.rmi.server";
        this.queuedAt = new Date();
    }

}
