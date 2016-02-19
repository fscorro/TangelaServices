package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class WorkInTest {

    private WorkIn workIn;

    @Test
    public void testInit() {
        this.workIn = WorkInFixture.withDefaults();
        assertNotNull(this.workIn);
        assertNotNull(this.workIn.rid());
        assertNotNull(this.workIn.in());
        assertNotNull(this.workIn.out());
        assertNotNull(this.workIn.version());
        assertNotNull(this.workIn.clazz());
    }
}
