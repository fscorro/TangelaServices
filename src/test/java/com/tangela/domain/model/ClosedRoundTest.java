package com.tangela.domain.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClosedRoundTest {

    private ClosedRound closedRound;

    @Test
    public void testInit() {
        this.closedRound = ClosedRoundFixture.withDefaults();
        assertNotNull(this.closedRound);
        assertNotNull(this.closedRound.rid());
        assertNotNull(this.closedRound.in());
        assertNotNull(this.closedRound.out());
        assertNotNull(this.closedRound.version());
        assertNotNull(this.closedRound.clazz());
    }
}
