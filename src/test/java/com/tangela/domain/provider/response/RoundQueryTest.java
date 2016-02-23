package com.tangela.domain.provider.response;

import com.tangela.domain.model.RoundFixture;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoundQueryTest {

    private RoundQuery roundQuery;

    @Test
    public void testInit(){
        this.roundQuery = new RoundQuery(newArrayList(RoundFixture.withDefaults()));
        assertNotNull(this.roundQuery);
        assertEquals(this.roundQuery.rounds().size(), 1);
    }
}
