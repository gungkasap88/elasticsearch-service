package com.example.elasticsearchservice.service;

import com.example.elasticsearchservice.model.channel.VehicleResponChannel;
import com.example.elasticsearchservice.search.SearchRequestDTO;

import java.util.Date;
import java.util.List;

public interface VehicleService {

    VehicleResponChannel getById(String id);
    List<VehicleResponChannel> search(SearchRequestDTO dto);
    List<VehicleResponChannel> getAllVehiclesCreatedSince(Date date);
    List<VehicleResponChannel> searchCreatedSince(SearchRequestDTO dto, Date date);
    boolean index(VehicleResponChannel vehicle);
}
