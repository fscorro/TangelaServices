package com.tangela.domain.model;

public class LocationFixture {

    private Location buildCity() {
        return new Location("#10:0", 1, "City", 11L, "Vienna", "Vienna", "");
    }

    private Location buildCountry() {
        return new Location("#11:0", 1, "Country", 21L, "Austria", "Austria", "");
    }

    private Location buildContinent() {
        return new Location("#12:0", 1, "Continent", 31L, "Europe", "Europe", "");
    }

    private Location buildEarth() {
        return new Location("#13:0", 1, "Earth", 41L, "Earth", "Earth", "");
    }

    public static Location cityWithDefaults() { return new LocationFixture().buildCity(); }

    public static Location countryWithDefaults() { return new LocationFixture().buildCountry(); }

    public static Location continentWithDefaults() { return new LocationFixture().buildContinent(); }

    public static Location earthWithDefaults() { return new LocationFixture().buildEarth(); }
}
