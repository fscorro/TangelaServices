package com.tangela.domain.provider.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class Database {

    private Map<String, String> server;

    private List<OrientDBClass> classes;

    private List<Map<String, String>> clusters;

    @JsonCreator
    public Database(@JsonProperty("server") Map<String, String> server,
                    @JsonProperty("classes") List<OrientDBClass> classes,
                    @JsonProperty("clusters") List<Map<String, String>> clusters) {
        this.server = server;
        this.classes = classes;
        this.clusters = clusters;
    }

    @JsonProperty
    public Map<String, String> server() {
        return this.server;
    }

    @JsonProperty
    public List<OrientDBClass> classes() {
        return this.classes;
    }

    @JsonProperty
    public List<Map<String, String>> clusters() {
        return this.clusters;
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
