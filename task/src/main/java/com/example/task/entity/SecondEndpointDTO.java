package com.example.task.entity;

import java.util.List;

public class SecondEndpointDTO {

    private String countryISO2;
    private String countryName;
    private List<BanksDTO> swiftCodes;

    public SecondEndpointDTO(String countryISO2, String countryName) {
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
    }

    public String getCountryISO2() {
        return countryISO2;
    }

    public void setCountryISO2(String countryISO2) {
        this.countryISO2 = countryISO2;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<BanksDTO> getSwiftCodes() {
        return swiftCodes;
    }

    public void setSwiftCodes(List<BanksDTO> banksFromCountry) {
        this.swiftCodes = banksFromCountry;
    }
}