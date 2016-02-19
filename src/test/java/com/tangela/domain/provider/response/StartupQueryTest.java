package com.tangela.domain.provider.response;

import com.tangela.domain.model.StartupFixture;
import org.junit.Test;
import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.*;

public class StartupQueryTest {

    private StartupQuery startupQuery;

    @Test
    public void testInit() {
        this.startupQuery = new StartupQuery(newArrayList(StartupFixture.withDefaults()));
        assertNotNull(this.startupQuery);
        assertEquals(this.startupQuery.startups().size(), 1);
    }
}
