package com.example.task.service;

import com.example.task.dto.*;
import com.example.task.repository.BankRepository;
import com.example.task.model.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService implements BankServiceInterface {

    private final BankNameService bankNameService;
    private final CountryService countryService;
    private final AddressService addressService;
    private final BankRepository bankRepository;

    @Override
    public List<Bank> findAll() {
        return this.bankRepository.findAll();
    }

    @Override
    public Bank findById(int id) {
        return this.bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find bank by id"));
    }

    @Override
    public Bank save(Bank bank) {
        return this.bankRepository.save(bank);
    }

    @Override
    public void deleteById(int id) {
        this.bankRepository.deleteById(id);
    }

    public Bank findBySwiftCode(String swiftCode) {
        return this.bankRepository.findBySwiftCode(swiftCode);
    }

    public BankDetailsDTO findBankDetails(String swift_code) {
        BankDetailsDTO banks = this.bankRepository.findBankDetails(swift_code);
        return banks;
    }

    public List<BranchDTO> getBranchesOf(int id) {
        List<BranchDTO> branches = this.bankRepository.getBranchesOf(id);
        return branches;
    }

    public CountryDetailsDTO findCountryDetails(String countryISO2) {
        CountryDetailsDTO countries = this.bankRepository.findCountryDetails(countryISO2);
        return countries;
    }

    public List<BanksDTO> getBanksOf(String countryISO2) {
        List<BanksDTO> banks = this.bankRepository.findBanksOf(countryISO2);
        return banks;
    }

    public Bank createBankFromDTO(PostBankDTO dto) {

        if (findBySwiftCode(dto.getSwiftCode()) != null) {
            throw new RuntimeException("SWIFT code already exists.");
        }

        BankName bankName = bankNameService.findByBankName(dto.getBankName());
        if (bankName == null) {
            bankName = new BankName();
            bankName.setBankName(dto.getBankName());
            bankName = bankNameService.save(bankName);
        }

        Country country = countryService.findByCountryName(dto.getCountryName());
        if (country == null) {
            country = new Country();
            country.setCountryISO2(dto.getCountryISO2().toUpperCase());
            country.setCountryName(dto.getCountryName().toUpperCase());
            country = countryService.save(country);
        }

        Address address = addressService.findByAddress(dto.getAddress());
        if (address == null) {
            address = new Address();
            address.setAddress(dto.getAddress());
            address.setTownName(null);
            address.setTimeZone(null);
            address = addressService.save(address);
        }

        Bank bank = new Bank();
        bank.setSwiftCode(dto.getSwiftCode());
        bank.setHeadquarter(dto.getIsHeadquarter());
        bank.setBankName(bankName);
        bank.setCountry(country);
        bank.setAddress(address);
        bank.setCodeType(null);
        bank.setHeadquarter(null);

        return bankRepository.save(bank);
    }

}
