package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class OrientDBClass {

    private String name;
    private String superClass;
    private Long records;

    @JsonCreator
    public OrientDBClass(@JsonProperty("name") String name,
                         @JsonProperty("superClass") String superClass,
                         @JsonProperty("records") Long records) {
        this.name = name;
        this.superClass = superClass;
        this.records = records;
    }

    @JsonProperty
    public String name() { return this.name; }

    @JsonProperty
    public String superClass() { return this.superClass; }

    @JsonProperty
    public Long records() { return this.records; }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
