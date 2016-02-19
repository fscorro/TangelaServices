package com.tangela.domain.provider.orientdb;

import com.tangela.TangelaServicesApplication;
import com.tangela.domain.model.Market;
import com.tangela.domain.model.Startup;
import com.tangela.domain.model.WorkIn;
import com.tangela.domain.provider.response.StartupQuery;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.google.common.collect.Lists.newArrayList;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TangelaServicesApplication.class)
@ActiveProfiles("local")
public class OrientDBServiceConnectorIT {

    @Autowired
    private QueryGenerator queryGenerator;

    @Autowired
    private OrientDBServiceConnector orientDBServiceConnector;

    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testConnect() {
        orientDBServiceConnector.connect();
    }

    @Test
    public void testDatabase() {
        assertNotNull(orientDBServiceConnector.database());
    }

    @Test
    public void testDocument() {
        assertNotNull(orientDBServiceConnector.getDocument("12:1", Market.class, Market.FETCH_PLAN));
    }

    @Test
    public void testQueryGetStartupsFromMarket() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing"), null, null, null);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 1);
    }

    @Test
    public void testQueryGetStartupsFromSeveralMarkets() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), null, null, null);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 2);
    }

    @Test
    public void testQueryGetStartupsFromSeveralMarketsAndLocation() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), newArrayList("Vienna"), null, null);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 1);
    }

    @Test
    public void testQueryGetStartupsFromSeveralMarketsAndSeveralLocations() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), newArrayList("Vienna", "Manitoba"), null, null);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 2);
    }

    @Test
    public void testQueryGetStartupsFromSeveralMarketsAndQuality() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), null, 1, null);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 2);
    }

    @Test
    public void testQueryGetStartupsFromSeveralMarketsAndSeveralLocationsAndQuality() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), newArrayList("Vienna", "Manitoba"), 1, null);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 2);
    }

    @Test
    public void testQueryGetStartupsFromSeveralMarketsAndSeveralLocationsAndWrongQuality() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), newArrayList("Vienna", "Manitoba"), 2, null);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 0);
    }

    @Test
    public void testQueryGetStartupsFromSeveralMarketsAndCreatedAt() {
        DateTime dt = formatter.parseDateTime("2012-01-01 00:00:00");
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), null, null, dt);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 1);
    }

    @Test
    public void testQueryGetStartupsFromSeveralMarketsAndSeveralLocationsAndQualityAndCreatedAt() {
        DateTime dt = formatter.parseDateTime("2012-01-01 00:00:00");
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), newArrayList("Vienna", "Manitoba"), 1, dt);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 1);
    }

    @Test
    public void testQueryGetStartupsFromSeveralLocationsAndQualityAndCreatedAt() {
        DateTime dt = formatter.parseDateTime("2012-01-01 00:00:00");
        String query = queryGenerator.getStartupsQuery(null, newArrayList("Vienna", "Manitoba", "San Jose"), 1, dt);
        StartupQuery startupQuery = orientDBServiceConnector.query(query, StartupQuery.class, Startup.FETCH_PLAN);
        assertNotNull(startupQuery);
        assertEquals(startupQuery.startups().size(), 3);
    }
}
