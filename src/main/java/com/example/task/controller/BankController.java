package com.example.task.controller;

import com.example.task.dto.BranchDTO;
import com.example.task.dto.BankDetailsDTO;
import com.example.task.dto.PostBankDTO;
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
@RequestMapping("/bank")
public class BankController {

    private final BankService bankService;

    private final CountryService countryService;

    private final AddressService addressService;

    @GetMapping("/swift-codes")
    public List<Bank> findAllBanks() {
        List<Bank> banks = this.bankService.findAll();
        return banks;
    }

    @GetMapping("/swift-codes/{swift_code}")
    public BankDetailsDTO getBankDetails(@PathVariable String swift_code) {
        BankDetailsDTO firstEndpointDTO = this.bankService.findBankDetails(swift_code);
        List<BranchDTO> branches = this.bankService.getBranchesOf(this.bankService.findBySwiftCode(firstEndpointDTO.getSwiftCode()).getId());
        firstEndpointDTO.setBranches(branches);
        return firstEndpointDTO;
    }

    @DeleteMapping("/swift-codes/{swift_code}")
    public Map<String, String> deleteBankBySwiftCode(@PathVariable String swift_code) {
        Bank bank = this.bankService.findBySwiftCode(swift_code);
        this.bankService.deleteById(bank.getId());
        return Map.of("message", "Bank deleted");
    }

    @PostMapping("/swift-codes")
    public Map<String, String> postBank(@RequestBody PostBankDTO thirdEndpointDTO) {
        this.bankService.createBankFromDTO(thirdEndpointDTO);
        return Map.of("message", "Bank created");
    }

}
