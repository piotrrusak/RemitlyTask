package com.example.task.service;

import com.example.task.model.Bank;

import java.util.List;

public interface BankServiceInterface {

    public List<Bank> findAll();

    public Bank findById(int id);

    public Bank save(Bank bank);

    public void deleteById(int id);

}