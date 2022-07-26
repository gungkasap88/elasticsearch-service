package com.example.elasticsearchservice.controller;

import com.example.elasticsearchservice.model.channel.VehicleResponChannel;
import com.example.elasticsearchservice.search.SearchRequestDTO;
import com.example.elasticsearchservice.service.VehicleService;
import com.example.elasticsearchservice.service.helper.VehicleDummyDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    private VehicleDummyDataService vehicleDummyDataService;

    public VehicleController(VehicleDummyDataService vehicleDummyDataService) {
        this.vehicleDummyDataService = vehicleDummyDataService;
    }

    @PostMapping
    public void index(@RequestBody final VehicleResponChannel vehicle) {
        vehicleService.index(vehicle);
    }

    @PostMapping("/insertdummydata")
    public void insertDummyData() {
        vehicleDummyDataService.insertDummyData();
    }

    @GetMapping("/{id}")
    public VehicleResponChannel getById(@PathVariable final String id) {
        return vehicleService.getById(id);
    }

    @PostMapping("/search")
    public List<VehicleResponChannel> search(@RequestBody final SearchRequestDTO dto) {
        return vehicleService.search(dto);
    }

    @GetMapping("/search/{date}")
    public List<VehicleResponChannel> getAllVehiclesCreatedSince(
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date date) {
        return vehicleService.getAllVehiclesCreatedSince(date);
    }

    @PostMapping("/searchcreatedsince/{date}")
    public List<VehicleResponChannel> searchCreatedSince(
            @RequestBody final SearchRequestDTO dto,
            @PathVariable
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            final Date date) {
        return vehicleService.searchCreatedSince(dto, date);
    }


}
