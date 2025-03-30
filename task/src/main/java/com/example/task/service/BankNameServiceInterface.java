package com.example.task.service;

import com.example.task.entity.BankName;

import java.util.List;

public interface BankNameServiceInterface {

    public BankName findById(int id);

    public BankName save(BankName bankName);

    public void deleteById(int id);
}
