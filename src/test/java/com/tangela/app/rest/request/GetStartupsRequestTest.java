package com.tangela.app.rest.request;

import org.junit.Test;

import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

import static org.junit.Assert.*;

public class GetStartupsRequestTest {

    private GetStartupsRequest request;

    @Test
    public void testInit() {
        this.request = new GetStartupsRequest();
        assertNotNull(this.request);
        assertNull(this.request.getMarkets());
        assertNull(this.request.getLocations());
        assertNull(this.request.getQuality());
        assertNull(this.request.getCreatedAt());

        this.request.setMarkets("Testing");
        this.request.setLocations("Vienna");
        this.request.setQuality(1);
        this.request.setCreatedAt(now(UTC));

        assertEquals(this.request.getMarkets(), "Testing");
        assertEquals(this.request.getLocations(), "Vienna");
        assertEquals(this.request.getQuality().intValue(), 1);
        assertNotNull(this.request.getCreatedAt());
    }

    @Test
    public void testInitWithFields() {
        this.request = new GetStartupsRequest("Testing", "Vienna", 1, now(UTC));
        assertNotNull(this.request);
        assertEquals(this.request.getMarkets(), "Testing");
        assertEquals(this.request.getLocations(), "Vienna");
        assertEquals(this.request.getQuality().intValue(), 1);
        assertNotNull(this.request.getCreatedAt());
    }
}
