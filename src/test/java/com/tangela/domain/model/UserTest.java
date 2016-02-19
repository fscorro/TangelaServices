package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Test
    public void testInit(){
        this.user = UserFixture.withDefaults();
        assertNotNull(this.user);
        assertNotNull(this.user.rid());
        assertNotNull(this.user.angelId());
        assertNotNull(this.user.followersCount());
        assertNotNull(this.user.name());
        assertNotNull(this.user.angelUrl());
        assertNotNull(this.user.biography());
        assertNotNull(this.user.imageUrl());
        assertNotNull(this.user.blogUrl());
        assertNotNull(this.user.onlineBioUrl());
        assertNotNull(this.user.twitterUrl());
        assertNotNull(this.user.facebookUrl());
        assertNotNull(this.user.linkedinUrl());
        assertTrue(this.user.investor());
        assertNotNull(this.user.version());
        assertNotNull(this.user.clazz());
    }
}
