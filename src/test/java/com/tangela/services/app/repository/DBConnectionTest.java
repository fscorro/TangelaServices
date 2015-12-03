package com.tangela.services.app.repository;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

import org.junit.Test;

public class DBConnectionTest {

    @Test
    public void testGetDBConnection()
    {
        ODatabaseDocumentTx docTx = ODBConnection.openDocumentDB();

        docTx.begin();

        for (ODocument doc : docTx.browseClass("Country"))
        {
            System.out.println(doc.field("displayName").toString());
        }

        docTx.close();

        OObjectDatabaseTx objTx = ODBConnection.openObjectDB();

        objTx.begin();

        objTx.close();
    }
}
