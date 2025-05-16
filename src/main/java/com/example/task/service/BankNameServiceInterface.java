package com.example.task.service;

import com.example.task.model.BankName;

public interface BankNameServiceInterface {

    public BankName findById(int id);

    public BankName save(BankName bankName);

    public void deleteById(int id);
}
