package com.example.elasticsearchservice.service;

import com.example.elasticsearchservice.model.PersonRespon;
import com.example.elasticsearchservice.model.channel.PersonResponChannel;

import java.util.List;

public interface ElasticsearchService{

    PersonRespon save(PersonResponChannel requestPerson);

    PersonResponChannel find(String id);

    Iterable<PersonResponChannel> findAll();
}
