package com.tangela.core.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity(name="FundingTo")
public class FundingTo
{
	private Double mount;
	private String typeRaise;
	private Integer raiseId;
	private Date date;

	public FundingTo() {}
	
	public Double getMount()
	{
		return mount;
	}

	public void setMount(Double mount)
	{
		this.mount = mount;
	}

	public String getTypeRaise()
	{
		return typeRaise;
	}

	public void setTypeRaise(String typeRaise)
	{
		this.typeRaise = typeRaise;
	}

	public Integer getRaiseId()
	{
		return raiseId;
	}

	public void setRaiseId(Integer raiseId)
	{
		this.raiseId = raiseId;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
}