package com.tangela.core.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity(name="WorkAs")
public class WorkAs
{
	private Integer angelId;
	private String role;
	private Date createdAt;
	private Date endedAt;
	private boolean confirmed;
	
	public WorkAs() {}
	
	public Integer getAngelId()
	{
		return angelId;
	}
	public void setAngelId(Integer angelId)
	{
		this.angelId = angelId;
	}
	public String getRole()
	{
		return role;
	}
	public void setRole(String role)
	{
		this.role = role;
	}
	public Date getCreatedAt()
	{
		return createdAt;
	}
	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}
	public Date getEndedAt()
	{
		return endedAt;
	}
	public void setEndedAt(Date endedAt)
	{
		this.endedAt = endedAt;
	}
	public boolean isConfirmed()
	{
		return confirmed;
	}
	public void setConfirmed(boolean confirmed)
	{
		this.confirmed = confirmed;
	}
}