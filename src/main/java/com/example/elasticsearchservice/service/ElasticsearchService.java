package com.example.elasticsearchservice.service;

import com.example.elasticsearchservice.model.Person;
import com.example.elasticsearchservice.model.channel.PersonResponChannel;

public interface ElasticsearchService{

    Person save(PersonResponChannel requestPerson);

    PersonResponChannel find(String id);

    Iterable<PersonResponChannel> findAll();
}
