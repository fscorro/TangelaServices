package com.tangela.orientdb;

import org.junit.Test;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public class TestDBConnection
{
	@Test
	public void testGetDBConnection()
	{
		ODatabaseDocumentTx docTx = DBConnection.openDocumentDB();
		
		docTx.begin();

		for (ODocument doc : docTx.browseClass("Country")) 
		{
			System.out.println( doc.field("displayName") );
		}
		
		docTx.close();
		
		OObjectDatabaseTx objTx = DBConnection.openObjectDB();
		
		objTx.begin();
		
		objTx.close();
	}
}