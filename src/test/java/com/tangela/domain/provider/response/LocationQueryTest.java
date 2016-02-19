package com.tangela.domain.provider.response;

import com.tangela.domain.model.LocationFixture;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationQueryTest {

    private LocationQuery locationQuery;

    @Test
    public void testInit(){
        this.locationQuery = new LocationQuery(newArrayList(LocationFixture.cityWithDefaults()));
        assertNotNull(this.locationQuery);
        assertEquals(this.locationQuery.locations().size(), 1);
    }
}
