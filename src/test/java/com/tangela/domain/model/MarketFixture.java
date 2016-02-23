package com.tangela.domain.model;

public class MarketFixture {

    private String rid = "#22:0";
    private Integer version = 1;
    private Long angelId = 90L;
    private String name = "Technology";
    private String displayName = "Technology";
    private String angelUrl = "";

    private Market build() {
        return new Market(this.rid, this.version, this.angelId, this.name, this.displayName, this.angelUrl);
    }

    public static Market withDefaults() {
        return new MarketFixture().build();
    }
}
