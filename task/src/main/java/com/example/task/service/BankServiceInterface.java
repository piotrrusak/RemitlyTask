package com.example.task.service;

import com.example.task.entity.Bank;

import java.util.List;

public interface BankServiceInterface {

    public Bank findById(int id);

    public Bank save(Bank bank);

    public void deleteById(int id);

}
