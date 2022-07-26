package com.example.elasticsearchservice.service.impl;

import com.example.elasticsearchservice.model.Person;
import com.example.elasticsearchservice.model.channel.PersonResponChannel;
import com.example.elasticsearchservice.repository.PersonRepository;
import com.example.elasticsearchservice.service.ElasticsearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Value("${elasticsearch.url}")
    public String elasticsearchUrl;

    private final PersonRepository repository;

    @Autowired
    public ElasticsearchServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person save(PersonResponChannel requestPerson) {
        Person personRespon = new Person();
        PersonResponChannel personResponChannel = new PersonResponChannel();
        try {
            personResponChannel.setId(requestPerson.getId());
            personResponChannel.setName(requestPerson.getName());
            repository.save(personResponChannel);
            personRespon.setStatusCode("200");
            personRespon.setMessageCode("Data berhasil ditambahkan");
            personRespon.setData(personResponChannel);
        } catch (Exception e) {
            e.printStackTrace();
            personRespon.setStatusCode("9081");
            personRespon.setMessageCode("(MICROSERVICE) GENERAL ERROR");
            log.info("ERROR " + e.getMessage());
        }
        return personRespon;
    }

    @Override
    public PersonResponChannel find(String id) {
        Optional<PersonResponChannel> dataFind = repository.findById(id);
        return dataFind.get();
    }

    @Override
    public Iterable<PersonResponChannel> findAll() {
        Iterable<PersonResponChannel> person = repository.findAll();
        return person;
    }


}
