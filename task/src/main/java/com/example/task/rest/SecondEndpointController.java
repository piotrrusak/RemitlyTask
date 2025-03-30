package com.example.task.rest;

import com.example.task.entity.BanksDTO;
import com.example.task.entity.SecondEndpointDTO;
import com.example.task.service.BankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class SecondEndpointController {

    private BankService bankService;

    SecondEndpointController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("swift-codes/country/{countryISO2}")
    public SecondEndpointDTO getCountryDetails(@PathVariable String countryISO2) {
        SecondEndpointDTO secondEndpointDTO = this.bankService.findCountryDetails(countryISO2);
        List<BanksDTO> banks = this.bankService.getBanksOf(countryISO2);
        secondEndpointDTO.setSwiftCodes(banks);
        return secondEndpointDTO;
    }

}
