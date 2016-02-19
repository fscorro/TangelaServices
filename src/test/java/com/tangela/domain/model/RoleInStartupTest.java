package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class RoleInStartupTest {

    private RoleInStartup roleInStartup;

    @Test
    public void testInit(){
        this.roleInStartup = RoleInStartupFixture.withDefaults();
        assertNotNull(this.roleInStartup);
        assertNotNull(this.roleInStartup.rid());
        assertNotNull(this.roleInStartup.angelId());
        assertNotNull(this.roleInStartup.in());
        assertFalse(this.roleInStartup.confirmed());
        assertNotNull(this.roleInStartup.createdAt());
        assertNotNull(this.roleInStartup.role());
        assertNull(this.roleInStartup.endedAt());
        assertNotNull(this.roleInStartup.version());
        assertNotNull(this.roleInStartup.clazz());
    }
}
