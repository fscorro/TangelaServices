package com.tangela.app.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.Startup;

import java.util.List;

public class GetStartupsResponse {

    private List<Startup> startups;

    @JsonCreator
    public GetStartupsResponse(@JsonProperty("startups") List<Startup> startups) {
        this.startups = startups;
    }

    @JsonProperty
    public List<Startup> startups() { return this.startups; }
}
