package com.example.task.controller;

import com.example.task.dto.BanksDTO;
import com.example.task.dto.CountryDetailsDTO;
import com.example.task.service.BankService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/country")
public class CountryController {

    private final BankService bankService;

    @GetMapping("swift-codes/country/{countryISO2}")
    public CountryDetailsDTO getCountryDetails(@PathVariable String countryISO2) {
        CountryDetailsDTO secondEndpointDTO = this.bankService.findCountryDetails(countryISO2);
        List<BanksDTO> banks = this.bankService.getBanksOf(countryISO2);
        secondEndpointDTO.setSwiftCodes(banks);
        return secondEndpointDTO;
    }

}