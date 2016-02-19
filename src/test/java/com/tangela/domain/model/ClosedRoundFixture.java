package com.tangela.domain.model;

public class ClosedRoundFixture {

    private String rid = "#14:0";
    private Integer version = 1;
    private Round in = RoundFixture.withDefaults();
    private Startup out = StartupFixture.withDefaults();

    private ClosedRound build() {
        return new ClosedRound(this.rid, this.version, this.in, this.out);
    }

    public static ClosedRound withDefaults() {
        return new ClosedRoundFixture().build();
    }
}
