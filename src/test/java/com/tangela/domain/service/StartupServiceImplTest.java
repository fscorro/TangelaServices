package com.tangela.domain.service;

import com.tangela.domain.model.Startup;
import com.tangela.domain.model.StartupFixture;
import com.tangela.domain.provider.orientdb.OrientDBServiceConnector;
import com.tangela.domain.provider.orientdb.QueryGenerator;
import com.tangela.domain.provider.response.StartupQuery;
import com.tangela.domain.service.impl.StartupServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Matchers.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StartupServiceImplTest {

    @InjectMocks
    private StartupServiceImpl startupService;

    @Mock
    private OrientDBServiceConnector serviceConnector;

    @Mock
    private QueryGenerator queryGenerator;

    @Test
    public void testGetStartups() {
        StartupQuery startupQuery = new StartupQuery(newArrayList(StartupFixture.withDefaults()));
        when(queryGenerator.getStartupsQuery(newArrayList("Testing"), newArrayList("Vienna"), 1, null)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(startupQuery);

        List<Startup> startups = startupService.getStartups(newArrayList("Testing"), newArrayList("Vienna"), 1, null);

        assertNotNull(startups);
        assertEquals(startups.size(), 1);

        verify(queryGenerator, times(1)).getStartupsQuery(anyListOf(String.class), anyListOf(String.class), anyInt(), anyObject());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetStartupByAngelId() {
        StartupQuery startupQuery = new StartupQuery(newArrayList(StartupFixture.withDefaults()));
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(startupQuery);

        Startup startup = startupService.getByAngelId(100L);
        assertNotNull(startup);

        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetStartupByInvalidAngelId() {
        StartupQuery startupQuery = new StartupQuery(newArrayList());
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(startupQuery);
        Startup startup = startupService.getByAngelId(100L);
        assertNull(startup);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testGetStartupByInvalidAngelIdAndReturnNull() {
        when(queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(null);
        Startup startup = startupService.getByAngelId(100L);
        assertNull(startup);
        verify(queryGenerator, times(1)).getDocumentByAngelIdAndClass(anyLong(), anyString());
        verify(serviceConnector, times(1)).query(anyString(), anyObject(), anyString());
    }

    @Test
    public void testUpdateStartup() {
        Startup startup = StartupFixture.withDefaults();
        StartupQuery startupQuery = new StartupQuery(newArrayList(startup));
        when(queryGenerator.getDocumentByAngelIdAndClass(startup.angelId(), QueryGenerator.STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(startupQuery);
        when(serviceConnector.updateDocument(startup.rid().substring(1), startup, String.class)).thenReturn("{}");
        startup = startupService.update(startup);
        assertNotNull(startup);
    }

    @Test
    public void testUpdateStartupWithInvalidAngelId() {
        Startup startup = StartupFixture.withDefaults();
        StartupQuery startupQuery = new StartupQuery(newArrayList(startup));
        when(queryGenerator.getDocumentByAngelIdAndClass(43L, QueryGenerator.STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(startupQuery);
        startup = startupService.update(startup);
        assertNull(startup);
    }

    @Test
    public void testUpdateStartupWithInvalidJsonResponse() {
        String jsonResponse = "{";
        Startup startup = StartupFixture.withDefaults();
        StartupQuery startupQuery = new StartupQuery(newArrayList(startup));
        when(queryGenerator.getDocumentByAngelIdAndClass(startup.angelId(), QueryGenerator.STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(startupQuery);
        when(serviceConnector.updateDocument(startup.rid().substring(1), startup, String.class)).thenReturn(jsonResponse);
        startup = startupService.update(startup);
        assertNull(startup);
    }

    @Test
    public void testSaveStartup() {
        Startup startup = StartupFixture.withDefaults();
        when(serviceConnector.saveDocument(startup, Startup.class)).thenReturn(startup);
        startup = startupService.save(startup);
        assertNotNull(startup);
    }

    @Test
    public void testDeleteStartup() {
        Startup startup = StartupFixture.withDefaults();
        StartupQuery startupQuery = new StartupQuery(newArrayList(startup));
        when(queryGenerator.getDocumentByAngelIdAndClass(startup.angelId(), QueryGenerator.STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(startupQuery);
        doNothing().when(serviceConnector).deleteDocument(startup.rid().substring(1));
        startupService.delete(startup.angelId());
        verify(serviceConnector, times(1)).deleteDocument(startup.rid().substring(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteStartupInvalidAngelId() {
        Startup startup = StartupFixture.withDefaults();
        StartupQuery startupQuery = new StartupQuery(newArrayList(startup));
        when(queryGenerator.getDocumentByAngelIdAndClass(1L, QueryGenerator.STARTUP)).thenReturn("QUERY");
        when(serviceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN)).thenReturn(startupQuery);
        startupService.delete(startup.angelId());
    }
}
