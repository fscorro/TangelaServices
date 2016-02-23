package com.tangela.domain.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangela.domain.model.RoleInStartup;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.RoleInStartupQuery;
import com.tangela.domain.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RoleInStartupServiceImpl implements ModelService<RoleInStartup> {

    @Autowired
    private OrientDBServiceConnector serviceConnector;

    @Autowired
    private QueryGenerator queryGenerator;

    @Override
    public RoleInStartup getByAngelId(Long angelId) {
        String query = queryGenerator.getDocumentByAngelIdAndClass(angelId, QueryGenerator.ROLE_IN_STARTUP);
        RoleInStartupQuery rolesInStartup = serviceConnector.query(query, RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN);
        if(rolesInStartup == null || rolesInStartup.rolesInStartup().size() < 1) {
            return null;
        }
        return rolesInStartup.rolesInStartup().get(0);
    }

    @Override
    public RoleInStartup update(RoleInStartup entity) {
        RoleInStartup roleInStartup = getByAngelId(entity.angelId());
        if(roleInStartup == null) {
            return null;
        }
        String response = serviceConnector.updateDocument(roleInStartup.rid().substring(1), updateRoleInStartupByRoleInStartup(roleInStartup, entity), String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, RoleInStartup.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public RoleInStartup save(RoleInStartup entity) {
        return serviceConnector.saveDocument(entity, RoleInStartup.class);
    }

    @Override
    public void delete(Long angelId) {
        RoleInStartup roleInStartup = getByAngelId(angelId);
        if(roleInStartup == null) {
            throw new IllegalArgumentException();
        }
        serviceConnector.deleteDocument(roleInStartup.rid().substring(1));
    }

    private RoleInStartup updateRoleInStartupByRoleInStartup(RoleInStartup old, RoleInStartup roleInStartup) {
        old.setAngelId(roleInStartup.angelId());
        old.setConfirmed(roleInStartup.confirmed());
        old.setCreatedAt(roleInStartup.createdAt());
        old.setEndedAt(roleInStartup.endedAt());
        old.setRole(roleInStartup.role());
        return old;
    }
}
