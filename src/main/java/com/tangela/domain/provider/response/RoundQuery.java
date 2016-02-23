package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.Round;

import java.util.List;

public class RoundQuery {

    private List<Round> rounds;

    @JsonCreator
    public RoundQuery(@JsonProperty("result") List<Round> rounds) {
        this.rounds = rounds;
    }

    @JsonProperty
    public List<Round> rounds() { return this.rounds; }
}
