package com.tangela.core.model;

import java.util.Date;

import javax.persistence.Entity;

@Entity(name="Startup")
public class Startup
{
	private Integer angelId;
	private String name;
	private String angelUrl;
	private Integer quality;
	private Date createdAt;
	private Date updatedAt;
	private String logoUrl;
	private String thumbUrl;	
	private String productDescription;
	private String highConcept;
	private Integer followersCount;
	private String companyUrl;
	private String twitterUrl;
	private String blogUrl;
	private String videoUrl;
	
	public Startup() {}
	
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
	public String getAngelUrl()
	{
		return angelUrl;
	}
	public void setAngelUrl(String angelUrl)
	{
		this.angelUrl = angelUrl;
	}
	public Integer getQuality()
	{
		return quality;
	}
	public void setQuality(Integer quality)
	{
		this.quality = quality;
	}
	public Date getCreatedAt()
	{
		return createdAt;
	}
	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt()
	{
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt)
	{
		this.updatedAt = updatedAt;
	}
	public String getLogoUrl()
	{
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl)
	{
		this.logoUrl = logoUrl;
	}
	public String getThumbUrl()
	{
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl)
	{
		this.thumbUrl = thumbUrl;
	}
	public String getProductDescription()
	{
		return productDescription;
	}
	public void setProductDescription(String productDescription)
	{
		this.productDescription = productDescription;
	}
	public String getHighConcept()
	{
		return highConcept;
	}
	public void setHighConcept(String highConcept)
	{
		this.highConcept = highConcept;
	}
	public Integer getFollowersCount()
	{
		return followersCount;
	}
	public void setFollowersCount(Integer followersCount)
	{
		this.followersCount = followersCount;
	}
	public String getCompanyUrl()
	{
		return companyUrl;
	}
	public void setCompanyUrl(String companyUrl)
	{
		this.companyUrl = companyUrl;
	}
	public String getTwitterUrl()
	{
		return twitterUrl;
	}
	public void setTwitterUrl(String twitterUrl)
	{
		this.twitterUrl = twitterUrl;
	}
	public String getBlogUrl()
	{
		return blogUrl;
	}
	public void setBlogUrl(String blogUrl)
	{
		this.blogUrl = blogUrl;
	}
	public String getVideoUrl()
	{
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl)
	{
		this.videoUrl = videoUrl;
	}
}