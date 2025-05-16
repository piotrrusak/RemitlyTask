package com.example.task.service;

import com.example.task.repository.BankNameRepository;
import com.example.task.model.BankName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankNameService implements BankNameServiceInterface {

    private final BankNameRepository bankNameRepository;

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
