package com.tangela.domain.provider.response;

import com.tangela.domain.model.RoleInStartupFixture;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleInStartupQueryTest {

    private RoleInStartupQuery roleInStartupQuery;

    @Test
    public void testInit(){
        this.roleInStartupQuery = new RoleInStartupQuery(newArrayList(RoleInStartupFixture.withDefaults()));
        assertNotNull(this.roleInStartupQuery);
        assertEquals(this.roleInStartupQuery.rolesInStartup().size(), 1);
    }
}
