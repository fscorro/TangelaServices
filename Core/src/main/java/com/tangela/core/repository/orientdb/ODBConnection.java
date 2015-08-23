package com.tangela.core.repository.orientdb;

import com.orientechnologies.orient.core.db.OPartitionedDatabasePool;
import com.orientechnologies.orient.core.db.OPartitionedDatabasePoolFactory;
import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.tx.OTransaction.TXTYPE;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * This class wraps top level operations to work with an OrientDB Database
 * (Object, Graph, Document)
 */
public class ODBConnection
{

	public enum DBTYPE
	{
		DOCUMENT, OBJECT
	};

	static final ThreadLocal<OObjectDatabaseTx> localObjectTx = new ThreadLocal<OObjectDatabaseTx>();
	static final ThreadLocal<ODatabaseDocumentTx> localDocumentTx = new ThreadLocal<ODatabaseDocumentTx>();

	/**
	 * This opens the requested database type (Document or Object) and starts a
	 * transaction
	 * 
	 * @param type
	 *            - Transaction type
	 * @param db
	 *            - DatabaseType to open
	 */
	public static void begin(TXTYPE type, DBTYPE db)
	{
		if (db == DBTYPE.DOCUMENT)
		{
			openDocumentDB().begin(type);
		}
		else
		{
			openObjectDB().begin(type);
		}
	}

	/**
	 * Closes (or returns to the pool) all the opened databases (Document,
	 * Object, Graph) for the current thread
	 */
	public static void close()
	{
		closeDocument();
		closeObject();
	}

	/**
	 * Close the DocumentDatabase, if opened for the current thread.
	 */
	public static void closeDocument()
	{
		if (hasDocumentTx())
		{
			localDocumentTx.get().close();
			localDocumentTx.set(null);
		}
	}

	/**
	 * Close the ObjectDatabase, if opened for the current thread.
	 */
	public static void closeObject()
	{
		if (hasObjectTx())
		{
			localObjectTx.get().close();
			localObjectTx.set(null);
		}
	}

	/**
	 * Commits the transaction. Normally you will not need to call this. Every
	 * method annotated with @Transactional will do the transaction initiation,
	 * commit and rollback automatically
	 */
	public static void commit()
	{
		if (hasObjectTx() && localObjectTx.get().getTransaction().isActive())
		{
			localObjectTx.get().commit();
		}
		if (hasDocumentTx()
				&& localDocumentTx.get().getTransaction().isActive())
		{
			localDocumentTx.get().commit();
		}
	}

	/**
	 * Creates a new or acquires an existing DocumentDatabase from the pool for
	 * the current Thread.
	 * 
	 * @return
	 */
	public static ODatabaseDocumentTx openDocumentDB()
	{
		if (!hasDocumentTx() || localDocumentTx.get().isClosed())
		{
			ODBConfiguration pluginConf = ODBConfiguration.getInstance();
			OPartitionedDatabasePoolFactory factory = new OPartitionedDatabasePoolFactory();
			OPartitionedDatabasePool db = factory.get(pluginConf.getUrl(),
					pluginConf.getUsername(), pluginConf.getPassword());
			localDocumentTx.set(db.acquire());
		}
		return localDocumentTx.get();
	}

	/**
	 * Creates a new or acquires an existing ObjectDatabase from the pool for
	 * the current Thread.
	 * 
	 * @return
	 */
	public static OObjectDatabaseTx openObjectDB()
	{
		if (!hasObjectTx() || localObjectTx.get().isClosed())
		{
			ODBConfiguration pluginConf = ODBConfiguration.getInstance();
			OObjectDatabaseTx db = OObjectDatabasePool.global().acquire(
					pluginConf.getUrl(), pluginConf.getUsername(),
					pluginConf.getPassword());

			localObjectTx.set(db);
		}
		return localObjectTx.get();
	}

	/**
	 * Rolls back a transaction - if any was active
	 */
	public static void rollback()
	{
		if (hasObjectTx() && localObjectTx.get().getTransaction().isActive())
		{
			localObjectTx.get().rollback();
		}
		if (hasDocumentTx()
				&& localDocumentTx.get().getTransaction().isActive())
		{
			localDocumentTx.get().rollback();
		}
	}

	private static boolean hasDocumentTx()
	{
		return localDocumentTx.get() != null;
	}

	private static boolean hasObjectTx()
	{
		return localObjectTx.get() != null;
	}
}