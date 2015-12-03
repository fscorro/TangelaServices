package com.tangela.services.app.repository;


import org.junit.Assert;
import org.junit.Test;

public class ODBConfigurationTest {

    @Test
    public void testGetDBConfiguration()
    {
        ODBConfiguration conf = ODBConfiguration.getInstance();

        Assert.assertNotEquals(conf.getUrl(), "memory:temp");
        Assert.assertNotEquals(conf.getUsername(), "admin");
        Assert.assertNotEquals(conf.getPassword(), "admin");
        Assert.assertNotEquals(conf.getPackagePrefix(), "models.*");

        Assert.assertEquals(conf.getUrl(), "remote:localhost/Tangela");
        Assert.assertEquals(conf.getUsername(), "root");
        Assert.assertEquals(conf.getPassword(), "R3k&Do1ps");
        Assert.assertEquals(conf.getPackagePrefix(), "com.tangela.services.domain.model.*");

        Assert.assertEquals(conf.getOpenInView(), 3);
    }
}
