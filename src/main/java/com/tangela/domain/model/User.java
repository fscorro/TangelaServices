package com.tangela.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User
{
	public static final String FETCH_PLAN = "*:0";
	public static final String ENTITY = "User";

	private String rid;
	private Integer version;
	private String clazz;
	private Long angelId;
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
	private Boolean investor;

	@JsonCreator
	public User(@JsonProperty("@rid") String rid,
				@JsonProperty("@version") Integer version,
				@JsonProperty("angelId") Long angelId,
				@JsonProperty("followersCount") Integer followersCount,
				@JsonProperty("name") String name,
				@JsonProperty("angelUrl") String angelUrl,
				@JsonProperty("biography") String biography,
				@JsonProperty("imageUrl") String imageUrl,
				@JsonProperty("blogUrl") String blogUrl,
				@JsonProperty("onlineBioUrl") String onlineBioUrl,
				@JsonProperty("twitterUrl") String twitterUrl,
				@JsonProperty("facebookUrl") String facebookUrl,
				@JsonProperty("linkedinUrl") String linkedinUrl,
				@JsonProperty("investor") Boolean investor) {
		this.rid = rid;
		this.version = version;
		this.clazz = ENTITY;
		this.angelId = angelId;
		this.followersCount = followersCount;
		this.name = name;
		this.angelUrl = angelUrl;
		this.biography = biography;
		this.imageUrl = imageUrl;
		this.blogUrl = blogUrl;
		this.onlineBioUrl = onlineBioUrl;
		this.twitterUrl = twitterUrl;
		this.facebookUrl = facebookUrl;
		this.linkedinUrl = linkedinUrl;
		this.investor = investor;
	}

	@JsonProperty("@rid")
	public String rid() { return this.rid; }

	@JsonProperty("@version")
	public Integer version() { return this.version; }

	@JsonProperty("@class")
	public String clazz() {return this.clazz; }

	@JsonProperty
	public Long angelId()
	{
		return this.angelId;
	}

	@JsonProperty
	public Integer followersCount()
	{
		return this.followersCount;
	}

	@JsonProperty
	public String name()
	{
		return this.name;
	}

	@JsonProperty
	public String angelUrl()
	{
		return this.angelUrl;
	}

	@JsonProperty
	public String biography()
	{
		return this.biography;
	}

	@JsonProperty
	public String imageUrl()
	{
		return this.imageUrl;
	}

	@JsonProperty
	public String blogUrl()
	{
		return this.blogUrl;
	}

	@JsonProperty
	public String onlineBioUrl()
	{
		return this.onlineBioUrl;
	}

	@JsonProperty
	public String twitterUrl()
	{
		return this.twitterUrl;
	}

	@JsonProperty
	public String facebookUrl()
	{
		return this.facebookUrl;
	}

	@JsonProperty
	public String linkedinUrl()
	{
		return this.linkedinUrl;
	}

	@JsonProperty
	public Boolean investor()
	{
		return this.investor;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public void setAngelId(Long angelId) {
		this.angelId = angelId;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAngelUrl(String angelUrl) {
		this.angelUrl = angelUrl;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public void setOnlineBioUrl(String onlineBioUrl) {
		this.onlineBioUrl = onlineBioUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public void setFacebookUrl(String facebookUrl) {
		this.facebookUrl = facebookUrl;
	}

	public void setLinkedinUrl(String linkedinUrl) {
		this.linkedinUrl = linkedinUrl;
	}

	public void setInvestor(Boolean investor) {
		this.investor = investor;
	}
}