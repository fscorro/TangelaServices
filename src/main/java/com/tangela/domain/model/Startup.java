package com.tangela.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tangela.app.support.jackson.DateTimeJsonDeserializer;
import com.tangela.app.support.jackson.DateTimeJsonSerializer;
import org.joda.time.DateTime;

import java.util.Date;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Startup
{
	public static final String FETCH_PLAN = "*:0";
	public static final String ENTITY = "Startup";

	private String rid;
	private Integer version;
	private String clazz;
	private Long angelId;
	private String name;
	private String angelUrl;
	private Integer quality;
	private DateTime createdAt;
	private DateTime updatedAt;
	private String logoUrl;
	private String thumbUrl;
	private String productDescription;
	private String highConcept;
	private Integer followersCount;
	private String companyUrl;
	private String twitterUrl;
	private String blogUrl;
	private String videoUrl;

	@JsonCreator
	public Startup(@JsonProperty("@rid") String rid,
				   @JsonProperty("@version") Integer version,
				   @JsonProperty("angelId") Long angelId,
				   @JsonProperty("name") String name,
				   @JsonProperty("angelUrl") String angelUrl,
				   @JsonProperty("quality") Integer quality,
				   @JsonProperty("createdAt") DateTime createdAt,
				   @JsonProperty("updatedAt") DateTime updatedAt,
				   @JsonProperty("logoUrl") String logoUrl,
				   @JsonProperty("thumbUrl") String thumbUrl,
				   @JsonProperty("productDescription") String productDescription,
				   @JsonProperty("highConcept") String highConcept,
				   @JsonProperty("followersCount") Integer followersCount,
				   @JsonProperty("companyUrl") String companyUrl,
				   @JsonProperty("twitterUrl") String twitterUrl,
				   @JsonProperty("blogUrl") String blogUrl,
				   @JsonProperty("videoUrl") String videoUrl) {
		this.rid = rid;
		this.version = version;
		this.clazz = ENTITY;
		this.angelId = angelId;
		this.name = name;
		this.angelUrl = angelUrl;
		this.quality = quality;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.logoUrl = logoUrl;
		this.thumbUrl = thumbUrl;
		this.productDescription = productDescription;
		this.highConcept = highConcept;
		this.followersCount = followersCount;
		this.companyUrl = companyUrl;
		this.twitterUrl = twitterUrl;
		this.blogUrl = blogUrl;
		this.videoUrl = videoUrl;
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
		return angelId;
	}

	@JsonProperty
	public String name()
	{
		return name;
	}

	@JsonProperty
	public String angelUrl()
	{
		return angelUrl;
	}

	@JsonProperty
	public Integer quality()
	{
		return quality;
	}

	@JsonProperty
	@JsonDeserialize(using = DateTimeJsonDeserializer.class)
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public DateTime createdAt()
	{
		return createdAt;
	}

	@JsonProperty
	@JsonDeserialize(using = DateTimeJsonDeserializer.class)
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public DateTime updatedAt()
	{
		return updatedAt;
	}

	@JsonProperty
	public String logoUrl()
	{
		return logoUrl;
	}

	@JsonProperty
	public String thumbUrl()
	{
		return thumbUrl;
	}

	@JsonProperty
	public String productDescription()
	{
		return productDescription;
	}

	@JsonProperty
	public String highConcept()
	{
		return highConcept;
	}

	@JsonProperty
	public Integer followersCount()
	{
		return followersCount;
	}

	@JsonProperty
	public String companyUrl()
	{
		return companyUrl;
	}

	@JsonProperty
	public String twitterUrl()
	{
		return twitterUrl;
	}

	@JsonProperty
	public String blogUrl()
	{
		return blogUrl;
	}

	@JsonProperty
	public String videoUrl()
	{
		return videoUrl;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public void setAngelId(Long angelId) {
		this.angelId = angelId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAngelUrl(String angelUrl) {
		this.angelUrl = angelUrl;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setHighConcept(String highConcept) {
		this.highConcept = highConcept;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	public void setCompanyUrl(String companyUrl) {
		this.companyUrl = companyUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}
}