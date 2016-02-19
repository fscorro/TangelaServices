package com.tangela.domain.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class StartupTest {

    private Startup startup;

    @Test
    public void testInit() {
        this.startup = StartupFixture.withDefaults();
        assertNotNull(this.startup);
        assertNotNull(this.startup.rid());
        assertNotNull(this.startup.name());
        assertNotNull(this.startup.angelId());
        assertNotNull(this.startup.angelUrl());
        assertNotNull(this.startup.quality());
        assertNotNull(this.startup.createdAt());
        assertNotNull(this.startup.updatedAt());
        assertNotNull(this.startup.logoUrl());
        assertNotNull(this.startup.thumbUrl());
        assertNotNull(this.startup.productDescription());
        assertNotNull(this.startup.highConcept());
        assertNotNull(this.startup.followersCount());
        assertNotNull(this.startup.companyUrl());
        assertNotNull(this.startup.twitterUrl());
        assertNotNull(this.startup.blogUrl());
        assertNotNull(this.startup.videoUrl());
        assertNotNull(this.startup.version());
        assertNotNull(this.startup.clazz());
    }
}
