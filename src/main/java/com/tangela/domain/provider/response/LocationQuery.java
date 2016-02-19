package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.Location;

import java.util.List;

public class LocationQuery {

    private List<Location> locations;

    @JsonCreator
    public LocationQuery(@JsonProperty("result") List<Location> locations) {
        this.locations = locations;
    }

    @JsonProperty
    public List<Location> locations() { return this.locations; }
}
