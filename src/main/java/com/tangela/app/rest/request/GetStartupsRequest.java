package com.tangela.app.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tangela.app.support.jackson.DateTimeJsonDeserializer;
import org.joda.time.DateTime;

public class GetStartupsRequest {

    private String markets;
    private String locations;
    private Integer quality;
    private DateTime createdAt;

    public GetStartupsRequest(String markets, String locations, Integer quality, DateTime date) {
        this.markets = markets;
        this.locations = locations;
        this.quality = quality;
        this.createdAt = date;
    }

    public GetStartupsRequest() {}

    public String getMarkets() {
        return markets;
    }

    public void setMarkets(String markets) {
        this.markets = markets;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }
}
