package com.example.task.dao;

import com.example.task.entity.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeTypeRepository extends JpaRepository<CodeType, Integer> {
    CodeType findByCodeType(String dataCodeType);
}
