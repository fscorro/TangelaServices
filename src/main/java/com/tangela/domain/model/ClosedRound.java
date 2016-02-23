package com.tangela.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClosedRound {

    public static final String FETCH_PLAN = "*:1";
    public static final String ENTITY = "ClosedRound";

    private String rid;
    private Integer version;
    private String clazz;
    private Round in;
    private Startup out;

    @JsonCreator
    public ClosedRound(@JsonProperty("@rid") String rid,
                       @JsonProperty("@version") Integer version,
                       @JsonProperty("in") Round in,
                       @JsonProperty("out") Startup out) {
        this.rid = rid;
        this.version = version;
        this.clazz = ENTITY;
        this.in = in;
        this.out = out;
    }

    @JsonProperty("@rid")
    public String rid() { return this.rid; }

    @JsonProperty("@version")
    public Integer version() { return this.version; }

    @JsonProperty("@class")
    public String clazz() {return this.clazz; }

    @JsonProperty
    public Round in() { return this.in; }

    @JsonProperty
    public Startup out() { return this.out; }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }
}
