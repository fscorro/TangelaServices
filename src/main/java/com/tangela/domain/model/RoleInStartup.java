package com.tangela.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleInStartup extends RoleIn {

    public static final String ENTITY = "RoleInStartup";

    private Startup out;

    @JsonCreator
    public RoleInStartup(@JsonProperty("@rid") String rid,
                         @JsonProperty("@version") Integer version,
                         @JsonProperty("angelId") Long angelId,
                         @JsonProperty("role") String role,
                         @JsonProperty("createdAt") DateTime createdAt,
                         @JsonProperty("endedAt") DateTime endedAt,
                         @JsonProperty("confirmed") Boolean confirmed,
                         @JsonProperty("in") Startup in,
                         @JsonProperty("out") Startup out) {
        super(rid, version, angelId, role, createdAt, endedAt, confirmed, in);
        this.clazz = ENTITY;
        this.out = out;
    }

    @JsonProperty
    public Startup out() { return this.out; }
}
