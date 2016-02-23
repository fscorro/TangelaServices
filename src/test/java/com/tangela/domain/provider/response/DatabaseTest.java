package com.tangela.domain.provider.response;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    private Map<String, String> servers;
    private List<OrientDBClass> classes;
    private List<Map<String, String>> clusters;

    @Before
    public void setUp(){
        this.servers = of("name", "name");
        this.classes = newArrayList(new OrientDBClass("BelongTo", "E", 18L));
        this.clusters = newArrayList(of("cluster", "cluster"));
    }

    @Test
    public void testInit(){
        this.database = new Database(servers, classes, clusters);
        assertNotNull(this.database);
        assertNotNull(this.database.server());
        assertEquals(this.database.server(), this.servers);
        assertNotNull(this.database.classes());
        assertEquals(this.database.classes(), this.classes);
        assertNotNull(this.database.clusters());
        assertEquals(this.database.clusters(), this.clusters);
    }
}
