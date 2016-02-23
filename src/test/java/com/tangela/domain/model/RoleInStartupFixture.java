package com.tangela.domain.model;

public class RoleInStartupFixture extends RoleInFixture{

    private RoleInStartup build() {
        return new RoleInStartup(this.rid, this.version, this.angelId, this.role, this.createdAt,
                this.endedAt, this.confirmed, this.startup, null);
    }

    public static RoleInStartup withDefaults() {
        return new RoleInStartupFixture().build();
    }
}
