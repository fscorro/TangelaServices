package com.tangela.domain.provider.orientdb;

import com.tangela.domain.model.BelongTo;
import com.tangela.domain.model.BelongToFixture;
import com.tangela.domain.model.Startup;
import com.tangela.domain.model.StartupFixture;
import com.tangela.domain.provider.exception.OrientDBException;
import com.tangela.domain.provider.response.Database;
import com.tangela.domain.provider.response.OrientDBClass;
import com.tangela.domain.provider.response.OrientDBClassTest;
import com.tangela.domain.provider.response.StartupQuery;
import com.tangela.domain.provider.support.AuthRestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.OK;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrientDBServiceConnectorTest {

    @InjectMocks
    private OrientDBServiceConnector orientDBServiceConnector;

    @Mock
    private AuthRestTemplate authRestTemplate;

    @Test
    public void testConnectOk() {
        ResponseEntity entity = new ResponseEntity(OK);
        when(authRestTemplate.get(anyString(), eq(null))).thenReturn(entity);
        orientDBServiceConnector.connect();
        verify(authRestTemplate, times(1)).get(anyString(), eq(null));
    }

    @Test(expected = OrientDBException.class)
    public void testConnectError() {
        ResponseEntity entity = new ResponseEntity(UNAUTHORIZED);
        when(authRestTemplate.get(anyString(), eq(null))).thenReturn(entity);
        orientDBServiceConnector.connect();
        verify(authRestTemplate, times(1)).get(anyString(), eq(null));
    }

    @Test
    public void testDatabaseOk() {
        Map<String, String> server = of("name", "name");
        List<OrientDBClass> classes = newArrayList(new OrientDBClass("BelongTo", "E", 18L));
        List<Map<String, String>> clusters = newArrayList(of("cluster", "cluster"));

        ResponseEntity<Database> entity = new ResponseEntity<>(new Database(server, classes, clusters), OK);
        when(authRestTemplate.get(anyString(), eq(Database.class))).thenReturn(entity);

        Database database = orientDBServiceConnector.database();
        assertNotNull(database);
        verify(authRestTemplate, times(1)).get(anyString(), eq(Database.class));
    }

    @Test(expected = OrientDBException.class)
    public void testDatabaseError() {
        ResponseEntity<Database> entity = new ResponseEntity<>(BAD_REQUEST);
        when(authRestTemplate.get(anyString(), eq(Database.class))).thenReturn(entity);
        orientDBServiceConnector.database();
        verify(authRestTemplate, times(1)).get(anyString(), eq(Database.class));
    }

    @Test
    public void testQuery() {
        ResponseEntity<StartupQuery> entity = new ResponseEntity<>(new StartupQuery(newArrayList(StartupFixture.withDefaults())), OK);
        when(authRestTemplate.get(anyString(), eq(StartupQuery.class))).thenReturn(entity);
        StartupQuery startupQuery = orientDBServiceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 1);
        verify(authRestTemplate, times(1)).get(anyString(), eq(StartupQuery.class));
    }

    @Test(expected = OrientDBException.class)
    public void testQueryError() {
        ResponseEntity<StartupQuery> entity = new ResponseEntity<>(BAD_REQUEST);
        when(authRestTemplate.get(anyString(), eq(StartupQuery.class))).thenReturn(entity);
        orientDBServiceConnector.query("QUERY", StartupQuery.class, Startup.FETCH_PLAN);
        verify(authRestTemplate, times(1)).get(anyString(), eq(StartupQuery.class));
    }

    @Test
    public void testDocument() {
        ResponseEntity<BelongTo> entity = new ResponseEntity<>(BelongToFixture.withDefaults(), OK);
        when(authRestTemplate.get(anyString(), eq(BelongTo.class))).thenReturn(entity);
        BelongTo belongTo = orientDBServiceConnector.getDocument("12:0", BelongTo.class, BelongTo.FETCH_PLAN);
        assertNotNull(belongTo);
        verify(authRestTemplate, times(1)).get(anyString(), eq(BelongTo.class));
    }

    @Test(expected = OrientDBException.class)
    public void testDocumentError() {
        ResponseEntity<BelongTo> entity = new ResponseEntity<>(BAD_REQUEST);
        when(authRestTemplate.get(anyString(), eq(BelongTo.class))).thenReturn(entity);
        orientDBServiceConnector.getDocument("12:0", BelongTo.class, BelongTo.FETCH_PLAN);
        verify(authRestTemplate, times(1)).get(anyString(), eq(BelongTo.class));
    }

    @Test
    public void testUpdateDocument() {
        BelongTo belongTo = BelongToFixture.withDefaults();
        ResponseEntity<BelongTo> entity = new ResponseEntity<>(belongTo, OK);
        when(authRestTemplate.put(anyString(), anyObject(), eq(BelongTo.class))).thenReturn(entity);
        BelongTo belongToResponse = orientDBServiceConnector.updateDocument("12:0", belongTo, BelongTo.class);
        assertNotNull(belongToResponse);
        verify(authRestTemplate, times(1)).put(anyString(), anyObject(), eq(BelongTo.class));
    }

    @Test(expected = OrientDBException.class)
    public void testUpdateDocumentError() {
        BelongTo belongTo = BelongToFixture.withDefaults();
        ResponseEntity<BelongTo> entity = new ResponseEntity<>(BAD_REQUEST);
        when(authRestTemplate.put(anyString(), anyObject(), eq(BelongTo.class))).thenReturn(entity);
        orientDBServiceConnector.updateDocument("12:0", belongTo, BelongTo.class);
        verify(authRestTemplate, times(1)).put(anyString(), anyObject(), eq(BelongTo.class));
    }

    @Test
    public void testSaveDocument() {
        BelongTo belongTo = BelongToFixture.withDefaults();
        ResponseEntity<BelongTo> entity = new ResponseEntity<>(belongTo, OK);
        when(authRestTemplate.post(anyString(), anyObject(), eq(BelongTo.class))).thenReturn(entity);
        BelongTo belongToResponse = orientDBServiceConnector.saveDocument(belongTo, BelongTo.class);
        assertNotNull(belongToResponse);
        verify(authRestTemplate, times(1)).post(anyString(), anyObject(), eq(BelongTo.class));
    }

    @Test(expected = OrientDBException.class)
    public void testSaveDocumentError() {
        BelongTo belongTo = BelongToFixture.withDefaults();
        ResponseEntity<BelongTo> entity = new ResponseEntity<>(BAD_REQUEST);
        when(authRestTemplate.post(anyString(), anyObject(), eq(BelongTo.class))).thenReturn(entity);
        orientDBServiceConnector.saveDocument(belongTo, BelongTo.class);
        verify(authRestTemplate, times(1)).post(anyString(), anyObject(), eq(BelongTo.class));
    }

    @Test
    public void testDeleteDocument() {
        ResponseEntity<String> entity = new ResponseEntity<>("RESPONSE", OK);
        when(authRestTemplate.delete(anyString(), eq(String.class))).thenReturn(entity);
        orientDBServiceConnector.deleteDocument("12:0");
        verify(authRestTemplate, times(1)).delete(anyString(), eq(String.class));
    }

    @Test(expected = OrientDBException.class)
    public void testDeleteDocumentError() {
        ResponseEntity<String> entity = new ResponseEntity<>(BAD_REQUEST);
        when(authRestTemplate.delete(anyString(), eq(String.class))).thenReturn(entity);
        orientDBServiceConnector.deleteDocument("12:0");
        verify(authRestTemplate, times(1)).delete(anyString(), eq(String.class));
    }
}
