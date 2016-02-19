package com.tangela.domain.provider.response;

import com.tangela.domain.model.UserFixture;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserQueryTest {

    private UserQuery userQuery;

    @Test
    public void testInit(){
        this.userQuery = new UserQuery(newArrayList(UserFixture.withDefaults()));
        assertNotNull(this.userQuery);
        assertEquals(this.userQuery.users().size(), 1);
    }
}
