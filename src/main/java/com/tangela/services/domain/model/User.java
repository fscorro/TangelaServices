package com.tangela.services.domain.model;

import javax.persistence.Entity;

@Entity(name="User")
public class User
{
	private Integer angelId;
	private Integer followersCount;
	private String name;
	private String angelUrl;
	private String biography;
	private String imageUrl;
	private String blogUrl;
	private String onlineBioUrl;
	private String twitterUrl;
	private String facebookUrl;
	private String linkedinUrl;
	private boolean investor;

	public User() {}
	
	public Integer getAngelId()
	{
		return angelId;
	}

	public void setAngelId(Integer angelId)
	{
		this.angelId = angelId;
	}

	public Integer getFollowersCount()
	{
		return followersCount;
	}

	public void setFollowersCount(Integer followersCount)
	{
		this.followersCount = followersCount;
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

	public String getBiography()
	{
		return biography;
	}

	public void setBiography(String biography)
	{
		this.biography = biography;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl(String imageUrl)
	{
		this.imageUrl = imageUrl;
	}

	public String getBlogUrl()
	{
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl)
	{
		this.blogUrl = blogUrl;
	}

	public String getOnlineBioUrl()
	{
		return onlineBioUrl;
	}

	public void setOnlineBioUrl(String onlineBioUrl)
	{
		this.onlineBioUrl = onlineBioUrl;
	}

	public String getTwitterUrl()
	{
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl)
	{
		this.twitterUrl = twitterUrl;
	}

	public String getFacebookUrl()
	{
		return facebookUrl;
	}

	public void setFacebookUrl(String facebookUrl)
	{
		this.facebookUrl = facebookUrl;
	}

	public String getLinkedinUrl()
	{
		return linkedinUrl;
	}

	public void setLinkedinUrl(String linkedinUrl)
	{
		this.linkedinUrl = linkedinUrl;
	}

	public boolean isInvestor()
	{
		return investor;
	}

	public void setInvestor(boolean investor)
	{
		this.investor = investor;
	}
}