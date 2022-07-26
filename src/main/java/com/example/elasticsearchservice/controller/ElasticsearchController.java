package com.example.elasticsearchservice.controller;

import com.example.elasticsearchservice.model.PersonRespon;
import com.example.elasticsearchservice.model.channel.PersonResponChannel;
import com.example.elasticsearchservice.service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class ElasticsearchController {

    @Autowired
    private ElasticsearchService elasticsearchService;

    @PostMapping
    public PersonRespon tambahData(@RequestBody final PersonResponChannel requestPerson) {
        return elasticsearchService.save(requestPerson);
    }

    @GetMapping(value = "/{id}")
    public PersonResponChannel findById(@PathVariable final String id) {
        return elasticsearchService.find(id);
    }

    @GetMapping(value = "/all")
    public Iterable<PersonResponChannel> findAllPerson(){
        return elasticsearchService.findAll();
    }

}
