package com.tangela.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangela.domain.model.User;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.StartupQuery;
import com.tangela.domain.provider.response.UserQuery;
import com.tangela.domain.service.UserService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OrientDBServiceConnector serviceConnector;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public List<User> getUsers(List<String> markets, List<String> locations, Integer quality, DateTime date) {
        String query = queryGenerator.getUsersFromStartupsQuery(markets, locations, quality, date);
        UserQuery userQueryQuery = serviceConnector.query(query, UserQuery.class, User.FETCH_PLAN);
        return userQueryQuery.users();
    }

    @Override
    public User getByAngelId(Long angelId) {
        String query = queryGenerator.getDocumentByAngelIdAndClass(angelId, QueryGenerator.USER);
        UserQuery users = serviceConnector.query(query, UserQuery.class, User.FETCH_PLAN);
        if(users == null || users.users().size() < 1) {
            return null;
        }
        return users.users().get(0);
    }

    @Override
    public User update(User entity) {
        User user = getByAngelId(entity.angelId());
        if(user == null) {
            return null;
        }
        String response = serviceConnector.updateDocument(user.rid().substring(1), updateUserbyUser(user, entity), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User save(User entity) {
        return serviceConnector.saveDocument(entity, User.class);
    }

    @Override
    public void delete(Long angelId) {
        User user = getByAngelId(angelId);
        if(user == null) {
            throw new IllegalArgumentException();
        }
        serviceConnector.deleteDocument(user.rid().substring(1));
    }

    private User updateUserbyUser(User old, User user) {
        old.setAngelId(user.angelId());
        old.setFollowersCount(user.followersCount());
        old.setName(user.name());
        old.setAngelUrl(user.angelUrl());
        old.setBiography(user.biography());
        old.setImageUrl(user.imageUrl());
        old.setBlogUrl(user.blogUrl());
        old.setOnlineBioUrl(user.onlineBioUrl());
        old.setTwitterUrl(user.twitterUrl());
        old.setFacebookUrl(user.facebookUrl());
        old.setLinkedinUrl(user.linkedinUrl());
        old.setInvestor(user.investor());
        return old;
    }
}
