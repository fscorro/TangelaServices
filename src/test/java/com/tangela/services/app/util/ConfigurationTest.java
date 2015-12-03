package com.tangela.services.app.util;

import org.junit.Assert;
import org.junit.Test;
import java.util.Properties;

public class ConfigurationTest {

    @Test
    public void testLoadFile()
    {
        Configuration conf = Configuration.getInstance();
        Properties p = conf.getProperties();
        String urlDB = p.getProperty("db.url");

        Assert.assertEquals(urlDB, "remote:localhost/Tangela");
        //Assert.assertEquals(urlDB, "remote:localhost:2480/Tangela.odb");
    }
}
