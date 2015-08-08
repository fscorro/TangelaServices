package com.tangela.util;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class TestConfiguration
{
	 @Test
	 public void testLoadFile()
	 {
		Configuration conf = Configuration.getInstance();
		Properties p = conf.getProperties();
		String urlDB = p.getProperty("db.url");
		assertEquals(urlDB, "remote:localhost:2480/Tangela.odb");
	 }
}