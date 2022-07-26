package com.example.elasticsearchservice.controller;

import com.example.elasticsearchservice.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @PostMapping("/recreate")
    public void recreateAllIndices() {
        indexService.recreateIndices(true);
    }
}
