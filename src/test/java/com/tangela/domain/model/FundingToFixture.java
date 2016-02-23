package com.tangela.domain.model;

import org.joda.time.DateTime;

import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

public class FundingToFixture {

    private String rid = "#19:0";
    private Integer version = 1;
    private Double mount = 23D;
    private String typeRaise = "Serie D";
    private Long raiseId = 234L;
    private DateTime date = now(UTC);

    private FundingTo build() {
        return new FundingTo(this.rid, this.version, this.mount, this.typeRaise, this.raiseId, this.date);
    }

    public static FundingTo withDefaults() {
        return new FundingToFixture().build();
    }
}
