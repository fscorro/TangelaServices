package com.tangela.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Configuration
{
	private static volatile Configuration instance;
	
	private static Properties prop = new Properties();

	private Configuration() 
	{
		InputStream input = null;
		try
		{
			String filename = "db.properties";
			input = Configuration.class.getClassLoader().getResourceAsStream(filename);
			if (input == null)
			{
				System.out.println("Sorry, unable to find " + filename);
				throw new FileNotFoundException();
			}

			// load a properties file from class path, inside static method
			prop.load(input);
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if (input != null)
			{
				try
				{
					input.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
     * Get the only instance of this class.
     *
     * @return the single instance.
     */
	public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }
	
	public Properties getProperties()
	{
		return prop;
	}
}