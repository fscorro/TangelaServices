package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class FundingToTest {

    private FundingTo fundingTo;

    @Test
    public void testInit(){
        this.fundingTo = FundingToFixture.withDefaults();
        assertNotNull(this.fundingTo);
        assertNotNull(this.fundingTo.rid());
        assertNotNull(this.fundingTo.date());
        assertNotNull(this.fundingTo.mount());
        assertNotNull(this.fundingTo.raiseId());
        assertNotNull(this.fundingTo.typeRaise());
        assertNotNull(this.fundingTo.version());
        assertNotNull(this.fundingTo.clazz());
    }
}
