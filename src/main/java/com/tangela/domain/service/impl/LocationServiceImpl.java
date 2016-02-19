package com.tangela.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangela.domain.model.Location;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.LocationQuery;
import com.tangela.domain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LocationServiceImpl implements ModelService<Location> {

    @Autowired
    private OrientDBServiceConnector serviceConnector;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public Location getByAngelId(Long angelId) {
        String query = queryGenerator.getDocumentByAngelIdAndClass(angelId, QueryGenerator.LOCATION);
        LocationQuery locations = serviceConnector.query(query, LocationQuery.class, Location.FETCH_PLAN);
        if(locations == null || locations.locations().size() < 1) {
            return null;
        }
        return locations.locations().get(0);
    }

    @Override
    public Location update(Location entity) {
        Location location = getByAngelId(entity.angelId());
        if(location == null) {
            return null;
        }
        String response = serviceConnector.updateDocument(location.rid().substring(1), updateLocationByLocation(location, entity), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, Location.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Location save(Location entity) {
        return serviceConnector.saveDocument(entity, Location.class);
    }

    @Override
    public void delete(Long angelId) {
        Location location = getByAngelId(angelId);
        if(location == null) {
            throw new IllegalArgumentException();
        }
        serviceConnector.deleteDocument(location.rid().substring(1));
    }

    private Location updateLocationByLocation(Location old, Location location) {
        old.setAngelId(location.angelId());
        old.setAngelUrl(location.angelUrl());
        old.setDisplayName(location.displayName());
        old.setName(location.name());
        return old;
    }
}
