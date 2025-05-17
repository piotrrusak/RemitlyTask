package com.example.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBankDTO {

    private String address;

    private String bankName;

    private String countryISO2;

    private String countryName;

    private boolean isHeadquarter;

    private String swiftCode;

    public PostBankDTO(String address, String bankName, String countryISO2, String countryName, boolean isHeadquarter, String swiftCode) {
        this.address = address;
        this.bankName = bankName;
        this.countryISO2 = countryISO2;
        this.countryName = countryName;
        this.isHeadquarter = isHeadquarter;
        this.swiftCode = swiftCode;
    }

}
