package com.example.task.repository;

import com.example.task.dto.BanksDTO;
import com.example.task.dto.BranchDTO;
import com.example.task.dto.BankDetailsDTO;
import com.example.task.dto.CountryDetailsDTO;
import com.example.task.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    Bank findBySwiftCode(String swiftCode);

    @Query(
    """
    SELECT b FROM Bank b
    JOIN FETCH b.bankName
    JOIN FETCH b.address
    JOIN FETCH b.country
    JOIN FETCH b.codeType
    """
    )
    List<Bank> findAllWithJoins();

    @Query(
            """
            SELECT new com.example.task.dto.BankDetailsDTO(
                a.address, bn.bankName, c.countryISO2, c.countryName, b.headquarter, b.swiftCode
            )
            FROM Bank b
            JOIN b.bankName bn
            JOIN b.country c
            JOIN b.address a
            WHERE b.swiftCode = :swift_code
            """
    )
    BankDetailsDTO findBankDetails(@Param("swift_code") String swift_code);

    @Query(
            """
            SELECT new com.example.task.dto.BranchDTO(
                a.address, bn.bankName, c.countryISO2, b.headquarter, b.swiftCode
            )
            FROM Bank b
            JOIN b.address a
            JOIN b.bankName bn
            JOIN b.country c
            WHERE b.headquarterBank.id = :id
            """
    )
    List<BranchDTO> getBranchesOf(@Param("id") int id);

    @Query(
            """
            SELECT new com.example.task.dto.CountryDetailsDTO(
                c.countryISO2, c.countryName
            )
            FROM Country c
            WHERE c.countryISO2 = :countryISO2
            """
    )
    CountryDetailsDTO findCountryDetails(@Param("countryISO2") String countryISO2);

    @Query(
            """
            SELECT new com.example.task.dto.BanksDTO(
                a.address, bn.bankName, c.countryISO2, b.headquarter, b.swiftCode
            )
            FROM Bank b
            JOIN b.country c
            JOIN b.address a
            JOIN b.bankName bn
            WHERE c.countryISO2 = :countryISO2
            """
    )
    List<BanksDTO> findBanksOf(@Param("countryISO2") String countryISO2);
}
