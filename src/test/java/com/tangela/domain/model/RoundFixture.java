package com.tangela.domain.model;

import org.joda.time.DateTime;

import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

public class RoundFixture {

    private String rid = "#9:0";
    private Integer version = 1;
    private Long angelId = 81L;
    private String name = "Round";
    private String roundType = "Series D";
    private String raised = "10,000,000";
    private DateTime roundClosedAt = now(UTC);
    private Long roundId = 101L;
    private String roundSourceUrl = "";
    private String participantName = "IDInvest Partners";
    private String participantType = "Startup";
    private String participantId = "45728";

    private Round build() {
        return new Round(this.rid, this.version, this.angelId, this.name, this.roundType, this.raised, this.roundClosedAt,
                this.roundId, this.roundSourceUrl, this.participantName, this.participantType, this.participantId);
    }

    public static Round withDefaults() {
        return new RoundFixture().build();
    }
}
