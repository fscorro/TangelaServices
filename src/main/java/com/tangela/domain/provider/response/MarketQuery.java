package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.Market;

import java.util.List;

public class MarketQuery {

    private List<Market> markets;

    @JsonCreator
    public MarketQuery(@JsonProperty("result") List<Market> markets) {
        this.markets = markets;
    }

    @JsonProperty
    public List<Market> markets() { return this.markets; }
}
