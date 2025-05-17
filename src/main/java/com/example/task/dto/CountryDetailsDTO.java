package com.example.task.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountryDetailsDTO {

    private String countryISO2;
    private String countryName;
    private List<BanksDTO> swiftCodes;

    public CountryDetailsDTO(String countryISO2, String countryName) {
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
    }

}