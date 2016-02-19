package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class ResideInTest {

    private ResideIn resideIn;

    @Test
    public void testInit() {
        this.resideIn = ResideInFixture.withDefaults();
        assertNotNull(this.resideIn);
        assertNotNull(this.resideIn.rid());
        assertNotNull(this.resideIn.in());
        assertNotNull(this.resideIn.out());
        assertNotNull(this.resideIn.version());
        assertNotNull(this.resideIn.clazz());
    }
}
