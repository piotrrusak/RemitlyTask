package com.example.task.rest;

import com.example.task.entity.Bank;
import com.example.task.service.BankService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class FourthEndpointController {

    private BankService bankService;

    FourthEndpointController(BankService bankService) {
        this.bankService = bankService;
    }

    @DeleteMapping("/swift-codes/{swift_code}")
    public Map<String, String> deleteBankBySwiftCode(@PathVariable String swift_code) {
        Bank bank = this.bankService.findBySwiftCode(swift_code);
        this.bankService.deleteById(bank.getId());
        return Map.of("message", "Bank deleted");
    }

}
