package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class LocationTest {

    private Location location;

    @Test
    public void testInitCity() {
        this.location = LocationFixture.cityWithDefaults();
        assertNotNull(this.location);
        assertNotNull(this.location.rid());
        assertNotNull(this.location.name());
        assertNotNull(this.location.displayName());
        assertNotNull(this.location.angelId());
        assertNotNull(this.location.angelUrl());
        assertNotNull(this.location.version());
        assertNotNull(this.location.clazz());

        assertFalse(this.location.equals(LocationFixture.countryWithDefaults()));
        assertTrue(this.location.equals(LocationFixture.cityWithDefaults()));
    }

    @Test
    public void testInitCountry() {
        this.location = LocationFixture.countryWithDefaults();
        assertNotNull(this.location);
        assertNotNull(this.location.rid());
        assertNotNull(this.location.name());
        assertNotNull(this.location.displayName());
        assertNotNull(this.location.angelId());
        assertNotNull(this.location.angelUrl());
        assertNotNull(this.location.version());
        assertNotNull(this.location.clazz());
    }

    @Test
    public void testInitContinent() {
        this.location = LocationFixture.continentWithDefaults();
        assertNotNull(this.location);
        assertNotNull(this.location.rid());
        assertNotNull(this.location.name());
        assertNotNull(this.location.displayName());
        assertNotNull(this.location.angelId());
        assertNotNull(this.location.angelUrl());
        assertNotNull(this.location.version());
        assertNotNull(this.location.clazz());
    }

    @Test
    public void testInitEarth() {
        this.location = LocationFixture.earthWithDefaults();
        assertNotNull(this.location);
        assertNotNull(this.location.rid());
        assertNotNull(this.location.name());
        assertNotNull(this.location.displayName());
        assertNotNull(this.location.angelId());
        assertNotNull(this.location.angelUrl());
        assertNotNull(this.location.version());
        assertNotNull(this.location.clazz());
    }
}
