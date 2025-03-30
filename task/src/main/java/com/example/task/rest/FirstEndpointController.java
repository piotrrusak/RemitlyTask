package com.example.task.rest;

import com.example.task.entity.BranchDTO;
import com.example.task.entity.FirstEndpointDTO;
import com.example.task.service.BankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class FirstEndpointController {

    private BankService bankService;

    public FirstEndpointController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/swift-codes/{swift_code}")
    public FirstEndpointDTO getBankDetails(@PathVariable String swift_code) {
        FirstEndpointDTO firstEndpointDTO = this.bankService.findBankDetails(swift_code);
        List<BranchDTO> branches = this.bankService.getBranchesOf(this.bankService.findBySwiftCode(firstEndpointDTO.getSwiftCode()).getId());
        firstEndpointDTO.setBranches(branches);
        return firstEndpointDTO;
    }
}
