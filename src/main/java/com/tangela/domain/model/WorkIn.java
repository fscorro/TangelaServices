package com.tangela.domain.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkIn
{
	public static final String FETCH_PLAN = "*:1";
	public static final String ENTITY = "WorkIn";

	private String rid;
	private Integer version;
	private String clazz;
	private Market in;
	private Startup out;

	@JsonCreator
	public WorkIn(@JsonProperty("@rid") String rid,
				  @JsonProperty("@version") Integer version,
				  @JsonProperty("in") Market in,
				  @JsonProperty("out") Startup out) {
		this.rid = rid;
		this.version = version;
		this.clazz = ENTITY;
		this.in = in;
		this.out = out;
	}

	@JsonProperty("@rid")
	public String rid() { return this.rid; }

	@JsonProperty("@version")
	public Integer version() { return this.version; }

	@JsonProperty("@class")
	public String clazz() {return this.clazz; }

	@JsonProperty
	public Market in() { return this.in; }

	@JsonProperty
	public Startup out() { return this.out; }
}