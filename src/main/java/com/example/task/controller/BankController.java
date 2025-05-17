package com.example.task.controller;

import com.example.task.dto.*;
import com.example.task.model.Bank;
import com.example.task.service.AddressService;
import com.example.task.service.BankService;
import com.example.task.service.CountryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BankController {

    private final BankService bankService;

    private final CountryService countryService;

    private final AddressService addressService;

    @GetMapping("/banks")
    public List<Bank> findAllBanks() {
        List<Bank> banks = this.bankService.findAll();
        return banks;
    }

    @GetMapping("/banks/{swift_code}")
    public BankDetailsDTO getBankDetails(@PathVariable String swift_code) {
        BankDetailsDTO bankDetailsDTO = this.bankService.findBankDetails(swift_code);
        List<BranchDTO> branches = this.bankService.getBranchesOf(this.bankService.findBySwiftCode(bankDetailsDTO.getSwiftCode()).getId());
        bankDetailsDTO.setBranches(branches);
        return bankDetailsDTO;
    }

    @DeleteMapping("/banks/{swift_code}")
    public Map<String, String> deleteBankBySwiftCode(@PathVariable String swift_code) {
        Bank bank = this.bankService.findBySwiftCode(swift_code);
        this.bankService.deleteById(bank.getId());
        return Map.of("message", "Bank deleted");
    }

    @PostMapping("/banks")
    public Map<String, String> postBank(@RequestBody PostBankDTO postBankDTO) {
        this.bankService.createBankFromDTO(postBankDTO);
        return Map.of("message", "Bank created");
    }

    @GetMapping("/banks/country/{countryISO2}")
    public CountryDetailsDTO getCountryBanks(@PathVariable String countryISO2) {
        CountryDetailsDTO countryDetailsDTO = this.bankService.findCountryDetails(countryISO2);
        List<BanksDTO> banks = this.bankService.getBanksOf(countryISO2);
        countryDetailsDTO.setSwiftCodes(banks);
        return countryDetailsDTO;
    }

}
