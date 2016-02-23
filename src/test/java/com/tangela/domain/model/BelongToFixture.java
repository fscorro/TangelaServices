package com.tangela.domain.model;

public class BelongToFixture {

    private String rid = "#10:0";
    private Integer version = 1;
    private Location continent = LocationFixture.continentWithDefaults();
    private Location country = LocationFixture.countryWithDefaults();

    private BelongTo build() {
        return new BelongTo(this.rid, this.version, this.continent, this.country);
    }

    public static BelongTo withDefaults() {
        return new BelongToFixture().build();
    }
}
