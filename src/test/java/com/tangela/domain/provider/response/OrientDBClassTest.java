package com.tangela.domain.provider.response;

import org.junit.Test;
import static org.junit.Assert.*;

public class OrientDBClassTest {

    private OrientDBClass orientDBClass;

    @Test
    public void testInit(){
        this.orientDBClass = new OrientDBClass("NAME", "SUPERCLASS", 3L);
        assertNotNull(this.orientDBClass);
        assertEquals(this.orientDBClass.name(), "NAME");
        assertEquals(this.orientDBClass.records().longValue(), 3L);
        assertEquals(this.orientDBClass.superClass(), "SUPERCLASS");
    }
}
