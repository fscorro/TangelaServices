package com.tangela.app.rest.response;

import com.tangela.domain.model.StartupFixture;
import org.junit.Test;

import static org.junit.Assert.*;

import static com.google.common.collect.Lists.newArrayList;

public class GetStartupsResponseTest {

    private GetStartupsResponse response;

    @Test
    public void testInit(){
        this.response = new GetStartupsResponse(newArrayList(StartupFixture.withDefaults()));
        assertNotNull(this.response);
        assertNotNull(this.response.startups());
        assertEquals(this.response.startups().size(), 1);
    }
}
