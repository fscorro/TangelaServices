package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.RoleInStartup;

import java.util.List;

public class RoleInStartupQuery {

    private List<RoleInStartup> rolesInStartup;

    @JsonCreator
    public RoleInStartupQuery(@JsonProperty("result") List<RoleInStartup> rolesInStartup) {
        this.rolesInStartup = rolesInStartup;
    }

    @JsonProperty
    public List<RoleInStartup> rolesInStartup() { return this.rolesInStartup; }
}
