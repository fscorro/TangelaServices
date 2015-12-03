package com.tangela.services.app.util;

import org.junit.Assert;
import org.junit.Test;
import java.util.Set;

public class ReflectionTest {

    @Test
    public void testReflection()
    {
        Reflection rf = new Reflection();
        Set<Class<?>> entitySet = rf.getTypesAnnotatedWith("com.tangela.services.domain.model", javax.persistence.Entity.class);
        Assert.assertTrue(entitySet.size() > 0);
    }
}
