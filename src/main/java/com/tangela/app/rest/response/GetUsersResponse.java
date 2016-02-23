package com.tangela.app.rest.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.User;

import java.util.List;

public class GetUsersResponse {

    private List<User> users;

    @JsonCreator
    public GetUsersResponse(@JsonProperty("users") List<User> users) {
        this.users = users;
    }

    @JsonProperty
    public List<User> users() { return this.users; }
}
