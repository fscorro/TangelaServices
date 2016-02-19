package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class BelongToTest {

    private BelongTo belongTo;

    @Test
    public void testInit() {
        this.belongTo = BelongToFixture.withDefaults();
        assertNotNull(this.belongTo);
        assertNotNull(this.belongTo.rid());
        assertNotNull(this.belongTo.in());
        assertNotNull(this.belongTo.out());
        assertNotNull(this.belongTo.version());
        assertNotNull(this.belongTo.clazz());

        assertFalse(this.belongTo.equals(null));
        assertTrue(this.belongTo.equals(BelongToFixture.withDefaults()));
    }
}
