package org.example.model;

public class Country {
    private String code;
    private String name;
    private String region;
    private String continent;


    public Country(String code, String name, String region, String continent) {
        this.code = code;
        this.name = name;
        this.region = region;
        this.continent = continent;
    }

    public Country(String code, String name, String region) {
        this.code = code;
        this.name = name;
        this.region = region;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", continent='" + continent + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

}
