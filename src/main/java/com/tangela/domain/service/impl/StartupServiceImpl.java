package com.tangela.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangela.domain.model.Startup;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.StartupQuery;
import com.tangela.domain.service.StartupService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StartupServiceImpl implements StartupService {

    @Autowired
    private OrientDBServiceConnector serviceConnector;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public List<Startup> getStartups(List<String> markets, List<String> locations, Integer quality, DateTime date) {
        String query = queryGenerator.getStartupsQuery(markets, locations, quality, date);
        StartupQuery startupQuery = serviceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        return startupQuery.startups();
    }

    @Override
    public Startup getByAngelId(final Long angelId) {
        String query = queryGenerator.getDocumentByAngelIdAndClass(angelId, QueryGenerator.STARTUP);
        StartupQuery startups = serviceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        if(startups == null || startups.startups().size() < 1) {
            return null;
        }
        return startups.startups().get(0);
    }

    @Override
    public Startup update(Startup entity) {
        Startup startup = getByAngelId(entity.angelId());
        if(startup == null) {
            return null;
        }
        String response = serviceConnector.updateDocument(startup.rid().substring(1), updateStartupByStartup(startup, entity), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, Startup.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Startup save(Startup entity) {
        return serviceConnector.saveDocument(entity, Startup.class);
    }

    @Override
    public void delete(Long angelId) {
        Startup startup = getByAngelId(angelId);
        if(startup == null) {
            throw new IllegalArgumentException();
        }
        serviceConnector.deleteDocument(startup.rid().substring(1));
    }

    private Startup updateStartupByStartup(Startup old, Startup startup) {
        old.setAngelId(startup.angelId());
        old.setName(startup.name());
        old.setAngelUrl(startup.angelUrl());
        old.setQuality(startup.quality());
        old.setCreatedAt(startup.createdAt());
        old.setUpdatedAt(startup.updatedAt());
        old.setLogoUrl(startup.logoUrl());
        old.setThumbUrl(startup.thumbUrl());
        old.setProductDescription(startup.productDescription());
        old.setHighConcept(startup.highConcept());
        old.setFollowersCount(startup.followersCount());
        old.setCompanyUrl(startup.companyUrl());
        old.setTwitterUrl(startup.twitterUrl());
        old.setBlogUrl(startup.blogUrl());
        old.setVideoUrl(startup.videoUrl());
        return old;
    }
}
