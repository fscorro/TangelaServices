package com.tangela.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Location
{
	public static final String FETCH_PLAN = "*:0";
	public static final String ENTITY = "Location";

	private String rid;
	private Integer version;
	private String clazz;
	private Long angelId;
	private String name;
	private String displayName;
	private String angelUrl;

	@JsonCreator
	public Location(@JsonProperty("@rid") String rid,
					@JsonProperty("@version") Integer version,
					@JsonProperty("@class") String clazz,
					@JsonProperty("angelId") Long angelId,
					@JsonProperty("name") String name,
					@JsonProperty("displayName") String displayName,
					@JsonProperty("angelUrl") String angelUrl) {
		this.rid = rid;
		this.version = version;
		this.clazz = clazz;
		this.angelId = angelId;
		this.name = name;
		this.displayName = displayName;
		this.angelUrl = angelUrl;
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
	public String name()
	{
		return this.name;
	}

	@JsonProperty
	public String displayName()
	{
		return this.displayName;
	}

	@JsonProperty
	public String angelUrl()
	{
		return this.angelUrl;
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

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setAngelUrl(String angelUrl) {
		this.angelUrl = angelUrl;
	}

	@Override
	public boolean equals(Object obj) {
		return reflectionEquals(this, obj);
	}
}