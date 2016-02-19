package com.tangela.domain.model;

public class ResideInFixture {

    private String rid = "#27:0";
    private Integer version = 1;
    private Location location = LocationFixture.cityWithDefaults();
    private Startup startup = StartupFixture.withDefaults();

    private ResideIn build() {
        return new ResideIn(this.rid, this.version, this.location, this.startup);
    }

    public static ResideIn withDefaults() {
        return new ResideInFixture().build();
    }
}

