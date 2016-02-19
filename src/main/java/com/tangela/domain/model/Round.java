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

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Round {

    public static final String FETCH_PLAN = "*:0";
    public static final String ENTITY = "Round";

    private String rid;
    private Integer version;
    private String clazz;
    private Long angelId;
    private String name;
    private String roundType;
    private String raised;
    private DateTime roundClosedAt;
    private Long roundId;
    private String roundSourceUrl;
    private String participantName;
    private String participantType;
    private String participantId;

    @JsonCreator
    public Round(@JsonProperty("@rid") String rid,
                 @JsonProperty("@version") Integer version,
                 @JsonProperty("angelId") Long angelId,
                 @JsonProperty("name") String name,
                 @JsonProperty("roundType") String roundType,
                 @JsonProperty("raised") String raised,
                 @JsonProperty("roundClosedAt") DateTime roundClosedAt,
                 @JsonProperty("roundId") Long roundId,
                 @JsonProperty("roundSourceUrl") String roundSourceUrl,
                 @JsonProperty("participantName") String participantName,
                 @JsonProperty("participantType") String participantType,
                 @JsonProperty("participantId") String participantId) {
        this.rid = rid;
        this.version = version;
        this.clazz = ENTITY;
        this.angelId = angelId;
        this.name = name;
        this.roundType = roundType;
        this.raised = raised;
        this.roundClosedAt = roundClosedAt;
        this.roundId = roundId;
        this.roundSourceUrl = roundSourceUrl;
        this.participantName = participantName;
        this.participantType = participantType;
        this.participantId = participantId;
    }

    @JsonProperty("@rid")
    public String rid() { return this.rid; }

    @JsonProperty("@version")
    public Integer version() { return this.version; }

    @JsonProperty("@class")
    public String clazz() {return this.clazz; }

    @JsonProperty
    public Long angelId() { return this.angelId; }

    @JsonProperty
    public String name() { return this.name; }

    @JsonProperty
    public String roundType() { return this.roundType; }

    @JsonProperty
    public String raised() { return this.raised; }

    @JsonProperty
    @JsonDeserialize(using = DateTimeJsonDeserializer.class)
    @JsonSerialize(using = DateTimeJsonSerializer.class)
    public DateTime roundClosedAt() { return this.roundClosedAt; }

    @JsonProperty
    public Long roundId() { return this.roundId; }

    @JsonProperty
    public String roundSourceUrl() { return this.roundSourceUrl; }

    @JsonProperty
    public String participantName() { return this.participantName; }

    @JsonProperty
    public String participantType() { return this.participantType; }

    @JsonProperty
    public String participantId() { return this.participantId; }

    public void setAngelId(Long angelId) {
        this.angelId = angelId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoundType(String roundType) {
        this.roundType = roundType;
    }

    public void setRaised(String raised) {
        this.raised = raised;
    }

    public void setRoundClosedAt(DateTime roundClosedAt) {
        this.roundClosedAt = roundClosedAt;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public void setRoundSourceUrl(String roundSourceUrl) {
        this.roundSourceUrl = roundSourceUrl;
    }

    public void setParticipantName(String participantName) {
        this.participantName = participantName;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    @Override
    public boolean equals(Object obj) {
        return reflectionEquals(this, obj);
    }
}
