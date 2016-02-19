package com.tangela.domain.provider.response;

import com.tangela.domain.model.MarketFixture;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MarketQueryTest {

    private MarketQuery marketQuery;

    @Test
    public void testInit(){
        this.marketQuery = new MarketQuery(newArrayList(MarketFixture.withDefaults()));
        assertNotNull(this.marketQuery);
        assertEquals(this.marketQuery.markets().size(), 1);
    }
}
