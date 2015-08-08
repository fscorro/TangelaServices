package com.tangela.util;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class TestReflection
{
	 @Test
	 public void testReflection()
	 {
		Reflection rf = new Reflection();
		Set<Class<?>> entitySet = rf.getTypesAnnotatedWith("com.tangela.core.model", javax.persistence.Entity.class);
		assertTrue(entitySet.size() > 0);
	 }
}