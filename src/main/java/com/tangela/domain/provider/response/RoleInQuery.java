package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.RoleIn;
import com.tangela.domain.model.RoleInStartup;

import java.util.List;

public class RoleInQuery {

    private List<RoleIn> rolesIn;

    @JsonCreator
    public RoleInQuery(@JsonProperty("result") List<RoleIn> rolesIn) {
        this.rolesIn = rolesIn;
    }

    @JsonProperty
    public List<RoleIn> rolesIn() { return this.rolesIn; }
}
