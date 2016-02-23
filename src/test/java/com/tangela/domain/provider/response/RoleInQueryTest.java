package com.tangela.domain.provider.response;

import com.tangela.domain.model.RoleInFixture;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RoleInQueryTest {

    private RoleInQuery roleInQuery;

    @Test
    public void testInit(){
        this.roleInQuery = new RoleInQuery(newArrayList(RoleInFixture.withDefaults()));
        assertNotNull(this.roleInQuery);
        assertEquals(this.roleInQuery.rolesIn().size(), 1);
    }
}
