package com.example.task.rest;

import com.example.task.entity.*;
import com.example.task.service.AddressService;
import com.example.task.service.BankService;
import com.example.task.service.CountryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class ThirdEndpointController {

    private BankService bankService;

    private CountryService countryService;

    private AddressService addressService;

    ThirdEndpointController(BankService bankService, CountryService countryService, AddressService addressService) {
        this.countryService = countryService;
        this.bankService = bankService;
        this.addressService = addressService;
    }

    @PostMapping("/swift-codes")
    public Map<String, String> postBank(@RequestBody ThirdEndpointDTO thirdEndpointDTO) {
        this.bankService.createBankFromDTO(thirdEndpointDTO);
        return Map.of("message", "Bank created");
    }

}
