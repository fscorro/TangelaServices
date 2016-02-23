package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class MarketTest {

    private Market market;

    @Test
    public void testInit(){
        this.market = MarketFixture.withDefaults();
        assertNotNull(this.market);
        assertNotNull(this.market.rid());
        assertNotNull(this.market.angelId());
        assertNotNull(this.market.angelUrl());
        assertNotNull(this.market.displayName());
        assertNotNull(this.market.name());
        assertNotNull(this.market.version());
        assertNotNull(this.market.clazz());
    }
}
