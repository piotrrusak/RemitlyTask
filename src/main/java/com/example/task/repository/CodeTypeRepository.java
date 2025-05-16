package com.example.task.repository;

import com.example.task.model.CodeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeTypeRepository extends JpaRepository<CodeType, Integer> {

    CodeType findByCodeType(String dataCodeType);

}
