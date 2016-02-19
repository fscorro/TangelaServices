package com.tangela.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangela.domain.model.RoleIn;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.RoleInQuery;
import com.tangela.domain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RoleInServiceImpl implements ModelService<RoleIn> {

    @Autowired
    private OrientDBServiceConnector serviceConnector;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public RoleIn getByAngelId(Long angelId) {
        String query = queryGenerator.getDocumentByAngelIdAndClass(angelId, QueryGenerator.ROLE_IN);
        RoleInQuery rolesIn = serviceConnector.query(query, RoleInQuery.class, RoleIn.FETCH_PLAN);
        if(rolesIn == null || rolesIn.rolesIn().size() < 1) {
            return null;
        }
        return rolesIn.rolesIn().get(0);
    }

    @Override
    public RoleIn update(RoleIn entity) {
        RoleIn roleIn = getByAngelId(entity.angelId());
        if(roleIn == null) {
            return null;
        }
        String response = serviceConnector.updateDocument(roleIn.rid().substring(1), updateRoleInByRoleIn(roleIn, entity), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, RoleIn.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RoleIn save(RoleIn entity) {
        return serviceConnector.saveDocument(entity, RoleIn.class);
    }

    @Override
    public void delete(Long angelId) {
        RoleIn roleIn = getByAngelId(angelId);
        if(roleIn == null) {
            throw new IllegalArgumentException();
        }
        serviceConnector.deleteDocument(roleIn.rid().substring(1));
    }

    private RoleIn updateRoleInByRoleIn(RoleIn old, RoleIn roleIn) {
        old.setAngelId(roleIn.angelId());
        old.setConfirmed(roleIn.confirmed());
        old.setCreatedAt(roleIn.createdAt());
        old.setEndedAt(roleIn.endedAt());
        old.setRole(roleIn.role());
        return old;
    }
}
