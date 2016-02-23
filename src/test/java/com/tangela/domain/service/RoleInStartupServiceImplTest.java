package com.tangela.domain.service;

import com.tangela.domain.model.RoleInStartup;
import com.tangela.domain.model.RoleInStartupFixture;
import com.tangela.domain.model.Round;
import com.tangela.domain.model.RoundFixture;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.RoleInStartupQuery;
import com.tangela.domain.provider.response.RoundQuery;
import com.tangela.domain.service.impl.RoleInStartupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleInStartupServiceImplTest {

    @InjectMocks
    private RoleInStartupServiceImpl roleInStartupService;

    @Mock
    private OrientDBServiceConnector serviceConnector;

    @Mock
    private QueryGenerator queryGenerator;

    @Test
    public void testGetRoleInStartupByAngelId() {
        RoleInStartupQuery roleInStartupQuery = new RoleInStartupQuery(newArrayList(RoleInStartupFixture.withDefaults()));
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROLE_IN_STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN)).thenReturn(roleInStartupQuery);
        RoleInStartup roleInStartup = roleInStartupService.getByAngelId(100L);
        assertNotNull(roleInStartup);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetRoleInStartupByInvalidAngelId() {
        RoleInStartupQuery roleInStartupQuery = new RoleInStartupQuery(newArrayList());
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROLE_IN_STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN)).thenReturn(roleInStartupQuery);
        RoleInStartup roleInStartup = roleInStartupService.getByAngelId(100L);
        assertNull(roleInStartup);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetRoleInStartupByInvalidAngelIdAndReturnNull() {
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROLE_IN_STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN)).thenReturn(null);
        RoleInStartup roleInStartup = roleInStartupService.getByAngelId(100L);
        assertNull(roleInStartup);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testUpdateRoleIn() {
        RoleInStartup roleInStartup = RoleInStartupFixture.withDefaults();
        RoleInStartupQuery roleInStartupQuery = new RoleInStartupQuery(newArrayList(roleInStartup));
        when(queryGenerator.getDocumentByAngelIdAndClass(roleInStartup.angelId(), QueryGenerator.ROLE_IN_STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN)).thenReturn(roleInStartupQuery);
        when(serviceConnector.updateDocument(roleInStartup.rid().substring(1), roleInStartup, String.class)).thenReturn("{}");
        roleInStartup = roleInStartupService.update(roleInStartup);
        assertNotNull(roleInStartup);
    }

    @Test
    public void testUpdateRoleInWithInvalidAngelId() {
        RoleInStartup roleInStartup = RoleInStartupFixture.withDefaults();
        RoleInStartupQuery roleInStartupQuery = new RoleInStartupQuery(newArrayList(roleInStartup));
        when(queryGenerator.getDocumentByAngelIdAndClass(43L, QueryGenerator.ROLE_IN_STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN)).thenReturn(roleInStartupQuery);
        roleInStartup = roleInStartupService.update(roleInStartup);
        assertNull(roleInStartup);
    }

    @Test
    public void testUpdateRoleInWithInvalidJsonResponse() {
        String jsonResponse = "{";
        RoleInStartup roleInStartup = RoleInStartupFixture.withDefaults();
        RoleInStartupQuery roleInStartupQuery = new RoleInStartupQuery(newArrayList(roleInStartup));
        when(queryGenerator.getDocumentByAngelIdAndClass(roleInStartup.angelId(), QueryGenerator.ROLE_IN_STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN)).thenReturn(roleInStartupQuery);
        when(serviceConnector.updateDocument(roleInStartup.rid().substring(1), roleInStartup, String.class)).thenReturn(jsonResponse);
        roleInStartup = roleInStartupService.update(roleInStartup);
        assertNull(roleInStartup);
    }

    @Test
    public void testSaveRoleIn() {
        RoleInStartup roleInStartup = RoleInStartupFixture.withDefaults();
        when(serviceConnector.saveDocument(roleInStartup, RoleInStartup.class)).thenReturn(roleInStartup);
        roleInStartup = roleInStartupService.save(roleInStartup);
        assertNotNull(roleInStartup);
    }

    @Test
    public void testDeleteRoleIn() {
        RoleInStartup roleInStartup = RoleInStartupFixture.withDefaults();
        RoleInStartupQuery roleInStartupQuery = new RoleInStartupQuery(newArrayList(roleInStartup));
        when(queryGenerator.getDocumentByAngelIdAndClass(roleInStartup.angelId(), QueryGenerator.ROLE_IN_STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN)).thenReturn(roleInStartupQuery);
        doNothing().when(serviceConnector).deleteDocument(roleInStartup.rid().substring(1));
        roleInStartupService.delete(roleInStartup.angelId());
        verify(serviceConnector, times(1)).deleteDocument(roleInStartup.rid().substring(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteRoleInInvalidAngelId() {
        RoleInStartup roleInStartup = RoleInStartupFixture.withDefaults();
        RoleInStartupQuery roleInStartupQuery = new RoleInStartupQuery(newArrayList(roleInStartup));
        when(queryGenerator.getDocumentByAngelIdAndClass(1L, QueryGenerator.ROLE_IN_STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInStartupQuery.class, RoleInStartup.FETCH_PLAN)).thenReturn(roleInStartupQuery);
        roleInStartupService.delete(roleInStartup.angelId());
    }
}
