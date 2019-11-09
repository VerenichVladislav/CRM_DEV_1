package com.example.aviasales2.entity.transferObjects;

public class CityDTO {
    private Long id;

    private String cityName;
    private String country;
    private Integer foundationDate;
    private Long population;

    public CityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(Integer foundationDate) {
        this.foundationDate = foundationDate;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
