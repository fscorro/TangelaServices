package com.tangela.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tangela.app.support.jackson.DateTimeJsonDeserializer;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FundingTo
{
	public static final String FETCH_PLAN = "*:1";
	public static final String ENTITY = "FundingTo";

	private String rid;
	private Integer version;
	private String clazz;
	private Double mount;
	private String typeRaise;
	private Long raiseId;
	private DateTime date;

	@JsonCreator
	public FundingTo(@JsonProperty("@rid") String rid,
					 @JsonProperty("@version") Integer version,
					 @JsonProperty("mount") Double mount,
					 @JsonProperty("typeRaise") String typeRaise,
					 @JsonProperty("raiseId") Long raiseId,
					 @JsonProperty("date") DateTime date) {
		this.rid = rid;
		this.version = version;
		this.clazz = ENTITY;
		this.mount = mount;
		this.typeRaise = typeRaise;
		this.raiseId = raiseId;
		this.date = date;
	}

	@JsonProperty("@rid")
	public String rid() { return this.rid; }

	@JsonProperty("@version")
	public Integer version() { return this.version; }

	@JsonProperty("@class")
	public String clazz() {return this.clazz; }

	@JsonProperty
	public Double mount()
	{
		return this.mount;
	}

	@JsonProperty
	public String typeRaise()
	{
		return this.typeRaise;
	}

	@JsonProperty
	@JsonDeserialize(using = DateTimeJsonDeserializer.class)
	public DateTime date()
	{
		return date;
	}

	@JsonProperty
	public Long raiseId()
	{
		return raiseId;
	}
}