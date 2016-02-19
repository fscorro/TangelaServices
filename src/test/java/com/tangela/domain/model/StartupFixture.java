package com.tangela.domain.model;

import org.joda.time.DateTime;
import static org.joda.time.DateTime.now;
import static org.joda.time.DateTimeZone.UTC;

public class StartupFixture {

    private String rid = "#7:0";
    private Integer version = 1;
    private Long angelId = 283561L;
    private String name = "Po-motion";
    private String angelUrl = "https://angel.co/po-motion-1";
    private Integer quality = 1;
    private DateTime createdAt = now(UTC);
    private DateTime updatedAt = now(UTC).plusDays(1);
    private String logoUrl = "";
    private String thumbUrl = "";
    private String productDescription = "";
    private String highConcept = "";
    private Integer followersCount = 1;
    private String companyUrl = "";
    private String twitterUrl = "";
    private String blogUrl = "";
    private String videoUrl = "";

    private Startup build() {
          return new Startup(this.rid, this.version, this.angelId, this.name, this.angelUrl, this.quality, this.createdAt,
                  this.updatedAt, this.logoUrl, this.thumbUrl, this.productDescription, this.highConcept,
                  this.followersCount, this.companyUrl, this.twitterUrl, this.blogUrl, this.videoUrl);
    }

    public static Startup withDefaults() {
        return new StartupFixture().build();
    }
}
