package com.example.task.service;

import com.example.task.dao.BankNameRepository;
import com.example.task.entity.Bank;
import com.example.task.entity.BankName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankNameService implements BankNameServiceInterface {

    private BankNameRepository bankNameRepository;

    BankNameService(BankNameRepository bankNameRepository) {
        this.bankNameRepository = bankNameRepository;
    }

    @Override
    public BankName findById(int id) {
        Optional<BankName> result = this.bankNameRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException("Couldn't find bank by id");
        }
    }

    @Override
    public BankName save(BankName bankName) {
        return this.bankNameRepository.save(bankName);
    }

    @Override
    public void deleteById(int id) {
        this.bankNameRepository.deleteById(id);
    }

    public BankName findByBankName(String bankName) {
        return this.bankNameRepository.findByBankName(bankName);
    }
}
