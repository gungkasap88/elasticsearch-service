package com.example.elasticsearchservice.service.impl;

import com.example.elasticsearchservice.helper.Indices;
import com.example.elasticsearchservice.model.channel.VehicleResponChannel;
import com.example.elasticsearchservice.search.SearchRequestDTO;
import com.example.elasticsearchservice.search.util.SearchUtil;
import com.example.elasticsearchservice.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class VehicleServiceImpl implements VehicleService {

    @Value("${elasticsearch.url}")
    public String elasticsearchUrl;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Logger LOG = LoggerFactory.getLogger(VehicleService.class);

    private final RestHighLevelClient client;

    public VehicleServiceImpl(RestHighLevelClient client) {
        this.client = client;
    }

    @Override
    public VehicleResponChannel getById(String id) {
        try {
            final GetResponse documentFields = client.get(
                    new GetRequest(Indices.VEHICLE_INDEX, id),
                    RequestOptions.DEFAULT
            );
            if (documentFields == null || documentFields.isSourceEmpty()) {
                return null;
            }

            return MAPPER.readValue(documentFields.getSourceAsString(), VehicleResponChannel.class);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * Used to search for vehicles based on data provided in the {@link SearchRequestDTO} DTO. For more info take a look
     * at DTO javadoc.
     *
     * @param dto DTO containing info about what to search for.
     * @return Returns a list of found vehicles.
     */
    @Override
    public List<VehicleResponChannel> search(SearchRequestDTO dto) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.VEHICLE_INDEX,
                dto
        );
        return searchInternal(request);
    }

    @Override
    public List<VehicleResponChannel> getAllVehiclesCreatedSince(Date date) {
        return null;
    }

    private List<VehicleResponChannel> searchInternal(final SearchRequest request) {
        if (request == null) {
            LOG.error("Failed to build search request");
            return Collections.emptyList();
        }

        try {
            final SearchResponse response = client.search(request, RequestOptions.DEFAULT);

            final SearchHit[] searchHits = response.getHits().getHits();
            final List<VehicleResponChannel> vehicles = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                vehicles.add(
                        MAPPER.readValue(hit.getSourceAsString(), VehicleResponChannel.class)
                );
            }
            return vehicles;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    /**
     * Used to get all vehicles that have been created since forwarded date.
     *
     * @param date Date that is forwarded to the search.
     * @return Returns all vehicles created since forwarded date.
     */
//    @Override
//    public List<VehicleResponChannel> getAllVehiclesCreatedSince(final Date date) {
//        final SearchRequest request = SearchUtil.buildSearchRequest(
//                Indices.VEHICLE_INDEX,
//                "created",
//                date
//        );
//        return searchInternal(request);
//    }

    @Override
    public List<VehicleResponChannel> searchCreatedSince(final SearchRequestDTO dto, final Date date) {
        final SearchRequest request = SearchUtil.buildSearchRequest(
                Indices.VEHICLE_INDEX,
                dto,
                date
        );

        return searchInternal(request);
    }

    @Override
    public boolean index(final VehicleResponChannel vehicle) {
        try {
            final String vehicleAsString = MAPPER.writeValueAsString(vehicle);

            final IndexRequest request = new IndexRequest(Indices.VEHICLE_INDEX);
            request.id(vehicle.getId());
            request.source(vehicleAsString, XContentType.JSON);

            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            return response != null && response.status().equals(RestStatus.OK);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
