package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.Startup;

import java.util.List;

public class StartupQuery {

    private List<Startup> startups;

    @JsonCreator
    public StartupQuery(@JsonProperty("result") List<Startup> startups) {
        this.startups = startups;
    }

    @JsonProperty
    public List<Startup> startups() { return this.startups; }
}
