package com.tangela.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tangela.app.support.jackson.DateTimeJsonDeserializer;
import com.tangela.app.support.jackson.DateTimeJsonSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleIn
{
	public static final String FETCH_PLAN = "*:1";
	public static final String ENTITY = "RoleIn";

	private String rid;
	private Integer version;
	protected String clazz;
	private Long angelId;

	/**
	 * [FOUNDER|EMPLOYEE|PAST_INVESTOR|CURRENT_INVESTOR|ADVISOR|INCUBATOR]
	 * [F|E|P|C|A|I]
	 */
	private String role;
	private DateTime createdAt;
	private DateTime endedAt;
	private Boolean confirmed;
	private Startup in;

	@JsonCreator
	public RoleIn(@JsonProperty("@rid") String rid,
				  @JsonProperty("@version") Integer version,
				  @JsonProperty("angelId") Long angelId,
				  @JsonProperty("role") String role,
				  @JsonProperty("createdAt") DateTime createdAt,
				  @JsonProperty("endedAt") DateTime endedAt,
				  @JsonProperty("confirmed") Boolean confirmed,
				  @JsonProperty("in") Startup in) {
		this.rid = rid;
		this.version = version;
		this.clazz = ENTITY;
		this.angelId = angelId;
		this.role = role;
		this.createdAt = createdAt;
		this.endedAt = endedAt;
		this.confirmed = confirmed;
		this.in = in;
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
	public String role()
	{
		return this.role;
	}

	@JsonProperty
	@JsonDeserialize(using = DateTimeJsonDeserializer.class)
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public DateTime createdAt()
	{
		return this.createdAt;
	}

	@JsonProperty
	@JsonDeserialize(using = DateTimeJsonDeserializer.class)
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	public DateTime endedAt()
	{
		return this.endedAt;
	}

	@JsonProperty
	public Boolean confirmed()
	{
		return this.confirmed;
	}

	@JsonProperty
	public Startup in() { return this.in; }

	public void setAngelId(Long angelId) {
		this.angelId = angelId;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setEndedAt(DateTime endedAt) {
		this.endedAt = endedAt;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}
}