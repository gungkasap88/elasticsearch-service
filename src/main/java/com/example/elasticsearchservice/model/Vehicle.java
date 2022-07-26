package com.example.elasticsearchservice.model;

import com.example.elasticsearchservice.model.channel.VehicleResponChannel;
import lombok.Data;

@Data
public class Vehicle {

    private String responseCode;
    private String responseMessage;
    private VehicleResponChannel data;

}
