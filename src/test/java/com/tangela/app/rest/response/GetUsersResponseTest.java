package com.tangela.app.rest.response;

import com.tangela.domain.model.UserFixture;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetUsersResponseTest {

    private GetUsersResponse response;

    @Test
    public void testInit(){
        this.response = new GetUsersResponse(newArrayList(UserFixture.withDefaults()));
        assertNotNull(this.response);
        assertNotNull(this.response.users());
        assertEquals(this.response.users().size(), 1);
    }
}
