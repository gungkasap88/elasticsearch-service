package com.example.elasticsearchservice.model.channel;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import lombok.Data;

@Data
public class VehicleResponChannel {

    private String id;
    private String number;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;
}
