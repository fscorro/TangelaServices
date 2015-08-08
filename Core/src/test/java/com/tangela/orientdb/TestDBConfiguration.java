package com.tangela.orientdb;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDBConfiguration
{
	 @Test
	 public void testGetDBConfiguration()
	 {
		 DBConfiguration conf = DBConfiguration.getInstance();
		 
		 assertNotEquals(conf.getUrl(), "memory:temp");
		 assertNotEquals(conf.getUsername(), "admin");
		 assertNotEquals(conf.getPassword(), "admin");
		 assertNotEquals(conf.getPackagePrefix(), "models.*");
		 
		 assertEquals(conf.getUrl(), "remote:localhost/Tangela");
		 assertEquals(conf.getUsername(), "root");
		 assertEquals(conf.getPassword(), "R3k&Do1ps");
		 assertEquals(conf.getPackagePrefix(), "com.tangela.core.model");

		 assertEquals(conf.getOpenInView(), 3);
	 }
}