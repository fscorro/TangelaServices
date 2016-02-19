package com.tangela.domain.service;

import com.tangela.domain.model.RoleIn;
import com.tangela.domain.model.RoleInFixture;
import com.tangela.domain.model.RoleInStartupFixture;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.RoleInQuery;
import com.tangela.domain.service.impl.RoleInServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleInServiceImplTest {

    @InjectMocks
    private RoleInServiceImpl roleInService;

    @Mock
    private OrientDBServiceConnector serviceConnector;

    @Mock
    private QueryGenerator queryGenerator;

    @Test
    public void testGetRoleInByAngelId() {
        RoleInQuery roleInQuery = new RoleInQuery(newArrayList(RoleInStartupFixture.withDefaults()));
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROLE_IN)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInQuery.class, RoleIn.FETCH_PLAN)).thenReturn(roleInQuery);
        RoleIn roleIn = roleInService.getByAngelId(100L);
        assertNotNull(roleIn);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetRoleInByInvalidAngelId() {
        RoleInQuery roleInQuery = new RoleInQuery(newArrayList());
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROLE_IN)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInQuery.class, RoleIn.FETCH_PLAN)).thenReturn(roleInQuery);
        RoleIn roleIn = roleInService.getByAngelId(100L);
        assertNull(roleIn);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetRoleInByInvalidAngelIdAndReturnNull() {
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.ROLE_IN)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInQuery.class, RoleIn.FETCH_PLAN)).thenReturn(null);
        RoleIn roleIn = roleInService.getByAngelId(100L);
        assertNull(roleIn);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testUpdateRoleIn() {
        RoleIn roleIn = RoleInFixture.withDefaults();
        RoleInQuery roleInQuery = new RoleInQuery(newArrayList(roleIn));
        when(queryGenerator.getDocumentByAngelIdAndClass(roleIn.angelId(), QueryGenerator.ROLE_IN)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInQuery.class, RoleIn.FETCH_PLAN)).thenReturn(roleInQuery);
        when(serviceConnector.updateDocument(roleIn.rid().substring(1), roleIn, String.class)).thenReturn("{}");
        roleIn = roleInService.update(roleIn);
        assertNotNull(roleIn);
    }

    @Test
    public void testUpdateRoleInWithInvalidAngelId() {
        RoleIn roleIn = RoleInFixture.withDefaults();
        RoleInQuery roleInQuery = new RoleInQuery(newArrayList(roleIn));
        when(queryGenerator.getDocumentByAngelIdAndClass(43L, QueryGenerator.ROLE_IN)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInQuery.class, RoleIn.FETCH_PLAN)).thenReturn(roleInQuery);
        roleIn = roleInService.update(roleIn);
        assertNull(roleIn);
    }

    @Test
    public void testUpdateRoleInWithInvalidJsonResponse() {
        String jsonResponse = "{";
        RoleIn roleIn = RoleInFixture.withDefaults();
        RoleInQuery roleInQuery = new RoleInQuery(newArrayList(roleIn));
        when(queryGenerator.getDocumentByAngelIdAndClass(roleIn.angelId(), QueryGenerator.ROLE_IN)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInQuery.class, RoleIn.FETCH_PLAN)).thenReturn(roleInQuery);
        when(serviceConnector.updateDocument(roleIn.rid().substring(1), roleIn, String.class)).thenReturn(jsonResponse);
        roleIn = roleInService.update(roleIn);
        assertNull(roleIn);
    }

    @Test
    public void testSaveRoleIn() {
        RoleIn roleIn = RoleInFixture.withDefaults();
        when(serviceConnector.saveDocument(roleIn, RoleIn.class)).thenReturn(roleIn);
        roleIn = roleInService.save(roleIn);
        assertNotNull(roleIn);
    }

    @Test
    public void testDeleteRoleIn() {
        RoleIn roleIn = RoleInFixture.withDefaults();
        RoleInQuery roleInQuery = new RoleInQuery(newArrayList(roleIn));
        when(queryGenerator.getDocumentByAngelIdAndClass(roleIn.angelId(), QueryGenerator.ROLE_IN)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInQuery.class, RoleIn.FETCH_PLAN)).thenReturn(roleInQuery);
        doNothing().when(serviceConnector).deleteDocument(roleIn.rid().substring(1));
        roleInService.delete(roleIn.angelId());
        verify(serviceConnector, times(1)).deleteDocument(roleIn.rid().substring(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteRoleInInvalidAngelId() {
        RoleIn roleIn = RoleInFixture.withDefaults();
        RoleInQuery roleInQuery = new RoleInQuery(newArrayList(roleIn));
        when(queryGenerator.getDocumentByAngelIdAndClass(1L, QueryGenerator.ROLE_IN)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", RoleInQuery.class, RoleIn.FETCH_PLAN)).thenReturn(roleInQuery);
        roleInService.delete(roleIn.angelId());
    }
}
