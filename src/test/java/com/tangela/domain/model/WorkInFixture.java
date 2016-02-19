package com.tangela.domain.model;

public class WorkInFixture {

    private String rid = "#5:0";
    private Integer version = 1;
    private Market market = MarketFixture.withDefaults();
    private Startup startup = StartupFixture.withDefaults();

    private WorkIn build() {
        return new WorkIn(this.rid, this.version, this.market, this.startup);
    }

    public static WorkIn withDefaults() {
        return new WorkInFixture().build();
    }
}
