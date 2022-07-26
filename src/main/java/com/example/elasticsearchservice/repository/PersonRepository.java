package com.example.elasticsearchservice.repository;

import com.example.elasticsearchservice.model.PersonRespon;
import com.example.elasticsearchservice.model.channel.PersonResponChannel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<PersonResponChannel, String> {
}
