package com.tangela.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangela.domain.model.Market;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.MarketQuery;
import com.tangela.domain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MarketServiceImpl implements ModelService<Market> {

    @Autowired
    private OrientDBServiceConnector serviceConnector;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public Market getByAngelId(Long angelId) {
        String query = queryGenerator.getDocumentByAngelIdAndClass(angelId, QueryGenerator.MARKET);
        MarketQuery markets = serviceConnector.query(query, MarketQuery.class, Market.FETCH_PLAN);
        if(markets == null || markets.markets().size() < 1) {
            return null;
        }
        return markets.markets().get(0);
    }

    @Override
    public Market update(Market entity) {
        Market market = getByAngelId(entity.angelId());
        if(market == null) {
            return null;
        }
        String response = serviceConnector.updateDocument(market.rid().substring(1), updateMarketByMarket(market, entity), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, Market.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Market save(Market entity) {
        return serviceConnector.saveDocument(entity, Market.class);
    }

    @Override
    public void delete(Long angelId) throws IllegalArgumentException {
        Market market = getByAngelId(angelId);
        if(market == null) {
            throw new IllegalArgumentException();
        }
        serviceConnector.deleteDocument(market.rid().substring(1));
    }

    private Market updateMarketByMarket(Market old, Market market) {
        old.setAngelId(market.angelId());
        old.setAngelUrl(market.angelUrl());
        old.setDisplayName(market.displayName());
        old.setName(market.name());
        return old;
    }
}
