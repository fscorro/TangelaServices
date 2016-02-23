package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoundTest {

    private Round round;

    @Test
    public void testInit() {
        this.round = RoundFixture.withDefaults();
        assertNotNull(this.round);
        assertNotNull(this.round.rid());
        assertNotNull(this.round.angelId());
        assertNotNull(this.round.name());
        assertNotNull(this.round.raised());
        assertNotNull(this.round.roundType());
        assertNotNull(this.round.roundClosedAt());
        assertNotNull(this.round.roundId());
        assertNotNull(this.round.roundSourceUrl());
        assertNotNull(this.round.participantId());
        assertNotNull(this.round.participantName());
        assertNotNull(this.round.participantType());
        assertNotNull(this.round.version());
        assertNotNull(this.round.clazz());
    }
}
