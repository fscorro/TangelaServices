package com.tangela.services.app.repository;

import com.tangela.services.app.util.Configuration;
import java.util.Properties;

public class ODBConfiguration 
{
	private static volatile ODBConfiguration instance;
	
	private ODBConfiguration() 
	{
		readConfig();
	}
	
	/**
     * Get the only instance of this class.
     *
     * @return the single instance.
     */
	public static ODBConfiguration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new ODBConfiguration();
                }
            }
        }
        return instance;
    }
	
	public enum ConfigKey 
	{
	    DB_URL("db.url"), /**/
	    DB_USERNAME("db.username"), /**/
	    DB_PASSWORD("db.password"), /**/
	    MODEL_PACKAGE_PREFIX("db.entities.package"), /**/
	    OPEN_IN_VIEW_DOCDB("db.open-in-view.documentdb"), /**/
	    OPEN_IN_VIEW_OBJECTDB("db.open-in-view.objectdb"), /**/
	    LOGGER("logger");
	    
	    private final String key;

	    private ConfigKey(String key) {
	        this.key = key;
	    }

	    public String getKey() {
	        return key;
	    }
	    
	}
	
	public static final int OIV_DOCUMENT_DB = 0x0001;
    public static final int OIV_OBJECT_DB = 0x002;
	
	/**
	 * The package prefix of all the classes that should be interesting for OrientDB's mapper
	 * Out of these only those classes are registered, which have the @Entity annotation
	 */
	private String packagePrefix;
	private int openInView;
	
	private String url;
	private String username;
	private String password;
	
	private void readConfig() 
	{
		Properties conf = Configuration.getInstance().getProperties();
		
    	url = readString(conf, ConfigKey.DB_URL, "memory:temp");
    	username = readString(conf, ConfigKey.DB_USERNAME, "admin");
    	password = readString(conf, ConfigKey.DB_PASSWORD, "admin");
    	
    	packagePrefix = readString(conf, ConfigKey.MODEL_PACKAGE_PREFIX, "models.*");
    	
    	boolean view = readBoolean(conf, ConfigKey.OPEN_IN_VIEW_DOCDB, true);
    	openInView |= view? OIV_DOCUMENT_DB : 0;
    	
    	view = readBoolean(conf, ConfigKey.OPEN_IN_VIEW_OBJECTDB, true);
    	openInView |= view? OIV_OBJECT_DB : 0;
	}
	
	public boolean isServerRemote() 
	{
		return url.startsWith("remote");
	}
    
    private static String readString(Properties cfg, ConfigKey key, String defValue) {
    	if(cfg == null)
    		return defValue;
    	
    	String val = cfg.getProperty(key.getKey());
    	return (val == null) ? defValue : val;
    }
    
    private static boolean readBoolean(Properties cfg, ConfigKey key, boolean defValue) {
    	if(cfg == null)
    		return defValue;
    	
    	Boolean val = new Boolean(cfg.getProperty(key.getKey()));
    	return (val == null) ? defValue : val;
    }

	public String getPackagePrefix()
	{
		return packagePrefix;
	}

	public void setPackagePrefix(String packagePrefix)
	{
		this.packagePrefix = packagePrefix;
	}

	public int getOpenInView()
	{
		return openInView;
	}

	public void setOpenInView(int openInView)
	{
		this.openInView = openInView;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}
}