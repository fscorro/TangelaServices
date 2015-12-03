package com.tangela.services.domain.model;

import javax.persistence.Entity;

@Entity(name="Location")
public class Location
{
	private Integer angelId;
	private String name;
	private String displayName;
	private String angelUrl;

	public Location() {}

	public Integer getAngelId()
	{
		return angelId;
	}

	public void setAngelId(Integer angelId)
	{
		this.angelId = angelId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(String displayName)
	{
		this.displayName = displayName;
	}

	public String getAngelUrl()
	{
		return angelUrl;
	}

	public void setAngelUrl(String angelUrl)
	{
		this.angelUrl = angelUrl;
	}
}