package com.tangela.core.repository.orientdb;

import java.util.List;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;
import com.orientechnologies.orient.core.exception.ORecordNotFoundException;
import com.orientechnologies.orient.core.id.ORID;
import com.orientechnologies.orient.core.sql.query.OSQLSynchQuery;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.tangela.core.repository.Repository;

public class RepositoryODB<T> implements Repository<T>
{
	/**
     * Opens a thread-local ObjectDatabase from the pool.
     * The DB is kept open until the method annotated with @Transactional finishes
     * or close() is called.
     * @return
     */
    public static OObjectDatabaseTx objectDB() 
    {
        return ODBConnection.openObjectDB();
    }
    
    /**
     * Opens a thread-local DocumentDB instance from the pool.
     * @return
     */
    public static ODatabaseDocumentTx documentDB() 
    {
        return ODBConnection.openDocumentDB();
    }

	@Override
	public T save(T instance)
	{
		return objectDB().save(instance);
	}

	@Override
	public T refresh(T instance)
	{
		objectDB().reload(instance);
        return instance;
	}

	@Override
	public void delete(T instance)
	{
		objectDB().delete(instance);
	}

	@Override
	public T findById(ORID id)
	{
		try 
		{
			return objectDB().load(id);
	    }
		catch (ORecordNotFoundException e)
		{
			return null;
	    }
	}

	@Override
	public List<T> find(String query, Object... params)
	{
		return objectDB().query(new OSQLSynchQuery<T>(query), params);
	}

	@Override
	public T newInstance(Class<T> clazz)
	{
		return objectDB().newInstance(clazz);
	}   
}