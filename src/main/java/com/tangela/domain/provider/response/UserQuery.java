package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tangela.domain.model.User;

import java.util.List;

public class UserQuery {

    private List<User> users;

    @JsonCreator
    public UserQuery(@JsonProperty("result") List<User> users) {
        this.users = users;
    }

    @JsonProperty
    public List<User> users() { return this.users; }
}
