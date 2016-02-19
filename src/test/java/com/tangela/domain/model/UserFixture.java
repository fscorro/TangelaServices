package com.tangela.domain.model;

public class UserFixture {

    private String rid = "#31:0";
    private Integer version = 1;
    private Long angelId = 98L;
    private Integer followersCount = 14;
    private String name = "Vienna Waithyes";
    private String angelUrl = "http://";
    private String biography = "biography";
    private String imageUrl = "http://";
    private String blogUrl = "http://";
    private String onlineBioUrl = "http://";
    private String twitterUrl = "http://";
    private String facebookUrl = "http://";
    private String linkedinUrl = "http://";
    private Boolean investor = true;

    private User build() {
        return new User(this.rid, this.version, this.angelId, this.followersCount, this.name, this.angelUrl,
                this.biography, this.imageUrl, this.blogUrl, this.onlineBioUrl, this.twitterUrl,
                this.facebookUrl, this.linkedinUrl, this.investor);
    }

    public static User withDefaults() {
        return new UserFixture().build();
    }
}

