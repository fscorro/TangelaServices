package com.tangela.domain.provider.orientdb;

import com.tangela.domain.model.*;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;

public class QueryGeneratorTest {

    private QueryGenerator queryGenerator;

    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    @Before
    public void setUp() {
        this.queryGenerator = new QueryGenerator();
    }

    @Test
    public void testGetStartupsByMarket(){
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing"), newArrayList(), null, null);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where out('WorkIn').name IN ['Testing']");
    }

    @Test
    public void testGetUsersByMarket() {
        String query = queryGenerator.getUsersFromStartupsQuery(newArrayList("Testing"), null, null, null);
        assertNotNull(query);
        assertEquals(query, "select expand(in('RoleIn')) from Startup where out('WorkIn').name IN ['Testing']");
    }

    @Test
    public void testGetStartupsByMarkets(){
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), null, null, null);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where out('WorkIn').name IN ['Testing','Technology']");
    }

    @Test
    public void testGetStartupsByMarketsAndLocations() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), newArrayList("Vienna", "Manitoba"), null, null);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where out('WorkIn').name IN ['Testing','Technology'] and out('ResideIn').name IN (select name from (traverse in(\"BelongTo\") from (select * from Location where name IN ['Vienna','Manitoba']) MAXDEPTH 3) where @class=\"City\")");
    }

    @Test
    public void testGetStartupsByLocation(){
        String query = queryGenerator.getStartupsQuery(newArrayList(), newArrayList("Vienna"), null, null);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where out('ResideIn').name IN (select name from (traverse in(\"BelongTo\") from (select * from Location where name IN ['Vienna']) MAXDEPTH 3) where @class=\"City\")");
    }

    @Test
    public void testGetStartupsByLocations(){
        String query = queryGenerator.getStartupsQuery(null, newArrayList("Vienna", "Manitoba"), null, null);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where out('ResideIn').name IN (select name from (traverse in(\"BelongTo\") from (select * from Location where name IN ['Vienna','Manitoba']) MAXDEPTH 3) where @class=\"City\")");
    }

    @Test
    public void testGetStartupsByMarketsAndLocationsAndQuality() {
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), newArrayList("Vienna", "Manitoba"), 1, null);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where out('WorkIn').name IN ['Testing','Technology'] and out('ResideIn').name IN (select name from (traverse in(\"BelongTo\") from (select * from Location where name IN ['Vienna','Manitoba']) MAXDEPTH 3) where @class=\"City\") and quality >= 1");
    }

    @Test
    public void testGetStartupsByQuality(){
        String query = queryGenerator.getStartupsQuery(null,null, 1, null);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where quality >= 1");
    }

    @Test
    public void testGetStartupsByMarketsAndLocationsAndQualityAndCreatedAt() {
        DateTime dt = formatter.parseDateTime("2012-01-01 00:00:00");
        String query = queryGenerator.getStartupsQuery(newArrayList("Testing", "Technology"), newArrayList("Vienna", "Manitoba"), 1, dt);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where out('WorkIn').name IN ['Testing','Technology'] and out('ResideIn').name IN (select name from (traverse in(\"BelongTo\") from (select * from Location where name IN ['Vienna','Manitoba']) MAXDEPTH 3) where @class=\"City\") and quality >= 1 and createdAt >= '2012-01-01 00:00:00'");
    }

    @Test
    public void testGetStartupsByCreatedAt(){
        DateTime dt = formatter.parseDateTime("2012-01-01 00:00:00");
        String query = queryGenerator.getStartupsQuery(null,null, null, dt);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where createdAt >= '2012-01-01 00:00:00'");
    }

    @Test
    public void testGetDocumentByAngelIdAndClass() {
        String query = queryGenerator.getDocumentByAngelIdAndClass(100L, QueryGenerator.STARTUP);
        assertNotNull(query);
        assertEquals(query, "select * from Startup where angelId = 100");
    }

    @Test
    public void testConstants() {
        assertEquals(QueryGenerator.BELONG_TO, BelongTo.class.getSimpleName());
        assertEquals(QueryGenerator.CLOSED_ROUND, ClosedRound.class.getSimpleName());
        assertEquals(QueryGenerator.FUNDING_TO, FundingTo.class.getSimpleName());
        assertEquals(QueryGenerator.LOCATION, Location.class.getSimpleName());
        assertEquals(QueryGenerator.MARKET, Market.class.getSimpleName());
        assertEquals(QueryGenerator.RESIDE_IN, ResideIn.class.getSimpleName());
        assertEquals(QueryGenerator.ROLE_IN, RoleIn.class.getSimpleName());
        assertEquals(QueryGenerator.ROLE_IN_STARTUP, RoleInStartup.class.getSimpleName());
        assertEquals(QueryGenerator.ROUND, Round.class.getSimpleName());
        assertEquals(QueryGenerator.STARTUP, Startup.class.getSimpleName());
        assertEquals(QueryGenerator.USER, User.class.getSimpleName());
        assertEquals(QueryGenerator.WORK_IN, WorkIn.class.getSimpleName());
    }
}
