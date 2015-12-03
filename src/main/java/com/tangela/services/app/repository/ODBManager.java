package com.tangela.services.app.repository;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.orientechnologies.orient.core.entity.OEntityManager;
import com.orientechnologies.orient.core.metadata.schema.OSchema;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;
import com.tangela.services.app.util.Reflection;

public class ODBManager
{
	private ODBConfiguration config;
	
	public ODBManager()
	{
		config = ODBConfiguration.getInstance();
		registerClasses();
	}
	
	/**
     * This method register classes annotated with @Entity.<br/>
     */
    private void registerClasses() 
    {
    	OObjectDatabaseTx db = ODBConnection.openObjectDB();
    	OEntityManager em = db.getEntityManager();
        OSchema schema = db.getMetadata().getSchema();
        Reflection rf = new Reflection();
        String[] toLoad = config.getPackagePrefix().split(",");
        
        Set<Class<?>> classes = new HashSet<Class<?>>();
        for(String load: toLoad)
        {
            load = load.trim();
            if(load.endsWith(".*"))
            {
            	Iterator<Class<?>> entitySet = rf.getTypesAnnotatedWith(load.substring(0, load.length()-2), 
            			javax.persistence.Entity.class).iterator();
    			while(entitySet.hasNext())
    				classes.add(entitySet.next());
            } 
            else 
            {
                try
                {
					classes.add(Class.forName(load));
				}
                catch (ClassNotFoundException e)
                {
                	// TODO Auto-generated catch block
            		e.printStackTrace();
				}
            }
        }
        
        /*
        * Register all classes in the set for OrientDB for Entity handling
        */
        for(Class<?> entity : classes)
        {
        	try
        	{
        		em.registerEntityClass(entity);
        		/* Create the same if does not exist yet */
        		if (!schema.existsClass(entity.getSimpleName()))
        		{
        			schema.createClass(entity);
        		}
        	} 
        	catch (Exception e)
        	{
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
        }
        
        db.close();
    }

	public ODBConfiguration getConfig()
	{
		return config;
	}

	public void setConfig(ODBConfiguration config)
	{
		this.config = config;
	}
}