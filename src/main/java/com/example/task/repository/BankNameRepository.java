package com.example.task.repository;

import com.example.task.model.BankName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankNameRepository extends JpaRepository<BankName, Integer> {

    public BankName findByBankName(String bankName);

}
