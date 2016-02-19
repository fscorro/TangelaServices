package com.tangela.domain.model;

import org.joda.time.DateTime;

import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

public class RoleInFixture {

    protected String rid = "#26:0";
    protected Integer version = 1;
    protected Long angelId = 47L;
    protected String role = "E";
    protected DateTime createdAt = now(UTC);
    protected DateTime endedAt = null;
    protected Boolean confirmed = false;
    protected Startup startup = StartupFixture.withDefaults();

    private RoleIn build() {
        return new RoleIn(this.rid, this.version, this.angelId, this.role, this.createdAt,
                this.endedAt, this.confirmed, this.startup);
    }

    public static RoleIn withDefaults() {
        return new RoleInFixture().build();
    }
}
