package com.tangela.domain.service;

import com.tangela.domain.model.User;
import org.joda.time.DateTime;

import java.util.List;

public interface UserService extends ModelService<User> {

    List<User> getUsers(List<String> markets, List<String> locations, Integer quality, DateTime date);
}
