package com.example.elasticsearchservice.model;

import com.example.elasticsearchservice.model.channel.PersonResponChannel;
import lombok.Data;

@Data
public class Person {

    private String statusCode;
    private String messageCode;
    private PersonResponChannel data;
}
