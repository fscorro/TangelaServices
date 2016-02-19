package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoleInTest {

    private RoleIn roleIn;

    @Test
    public void testInit(){
        this.roleIn = RoleInFixture.withDefaults();
        assertNotNull(this.roleIn);
        assertNotNull(this.roleIn.rid());
        assertNotNull(this.roleIn.angelId());
        assertNotNull(this.roleIn.in());
        assertFalse(this.roleIn.confirmed());
        assertNotNull(this.roleIn.createdAt());
        assertNotNull(this.roleIn.role());
        assertNull(this.roleIn.endedAt());
        assertNotNull(this.roleIn.version());
        assertNotNull(this.roleIn.clazz());
    }
}
