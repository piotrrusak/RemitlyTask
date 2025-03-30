package com.example.task.service;

import com.example.task.dao.BankRepository;
import com.example.task.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService implements BankServiceInterface {
    @Autowired
    private BankNameService bankNameService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private AddressService addressService;

    private BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
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

    public FirstEndpointDTO findBankDetails(String swift_code) {
        FirstEndpointDTO banks = this.bankRepository.findBankDetails(swift_code);
        return banks;
    }

    public List<BranchDTO> getBranchesOf(int id) {
        List<BranchDTO> branches = this.bankRepository.getBranchesOf(id);
        return branches;
    }

    public SecondEndpointDTO findCountryDetails(String countryISO2) {
        SecondEndpointDTO countries = this.bankRepository.findCountryDetails(countryISO2);
        return countries;
    }

    public List<BanksDTO> getBanksOf(String countryISO2) {
        List<BanksDTO> banks = this.bankRepository.findBanksOf(countryISO2);
        return banks;
    }

    public Bank createBankFromDTO(ThirdEndpointDTO dto) {
        // 1. Sprawdź duplikat SWIFT code
        if (findBySwiftCode(dto.getSwiftCode()) != null) {
            throw new RuntimeException("SWIFT code already exists.");
        }

        // 2. Bank name
        BankName bankName = bankNameService.findByBankName(dto.getBankName());
        if (bankName == null) {
            bankName = new BankName();
            bankName.setBankName(dto.getBankName());
            bankName = bankNameService.save(bankName);
        }

        // 3. Country
        Country country = countryService.findByCountryName(dto.getCountryName());
        if (country == null) {
            country = new Country();
            country.setCountryISO2(dto.getCountryISO2().toUpperCase()); // 👍 jak w wymaganiach
            country.setCountryName(dto.getCountryName().toUpperCase()); // 👍
            country = countryService.save(country);
        }

        // 4. Address
        Address address = addressService.findByAddress(dto.getAddress());
        if (address == null) {
            address = new Address();
            address.setAddress(dto.getAddress());
            address.setTownName(null);
            address.setTimeZone(null);
            address = addressService.save(address);
        }

        // 5. Zapisz nowy Bank
        Bank bank = new Bank();
        bank.setSwiftCode(dto.getSwiftCode());
        bank.setHeadquarter(dto.getIsHeadquarter()); // setter już działa na boolean
        bank.setBankName(bankName); // <-- teraz przekazujemy cały obiekt
        bank.setCountry(country);
        bank.setAddress(address);
        bank.setCodeType(null); // jeśli nie masz na razie typu SWIFT code
        bank.setHeadquarter(null); // jeśli to nie jest branch

        return bankRepository.save(bank);
    }

}
