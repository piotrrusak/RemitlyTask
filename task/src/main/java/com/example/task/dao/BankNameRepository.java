package com.example.task.dao;

import com.example.task.entity.BankName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankNameRepository extends JpaRepository<BankName, Integer> {

    public BankName findByBankName(String bankName);

}
