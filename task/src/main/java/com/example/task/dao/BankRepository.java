package com.example.task.dao;

import com.example.task.entity.*;
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
            SELECT new com.example.task.entity.FirstEndpointDTO(
                a.address, bn.bankName, c.countryISO2, c.countryName, b.isHeadquarter, b.swiftCode
            )
            FROM Bank b
            JOIN b.bankName bn
            JOIN b.country c
            JOIN b.address a
            WHERE b.swiftCode = :swift_code
            """
    )
    FirstEndpointDTO findBankDetails(@Param("swift_code") String swift_code);

    @Query(
            """
            SELECT new com.example.task.entity.BranchDTO(
                a.address, bn.bankName, c.countryISO2, b.isHeadquarter, b.swiftCode
            )
            FROM Bank b
            JOIN b.address a
            JOIN b.bankName bn
            JOIN b.country c
            WHERE b.headquarter.id = :id
            """
    )
    List<BranchDTO> getBranchesOf(@Param("id") int id);

    @Query(
            """
            SELECT new com.example.task.entity.SecondEndpointDTO(
                c.countryISO2, c.countryName
            )
            FROM Country c
            WHERE c.countryISO2 = :countryISO2
            """
    )
    SecondEndpointDTO findCountryDetails(@Param("countryISO2") String countryISO2);

    @Query(
            """
            SELECT new com.example.task.entity.BanksDTO(
                a.address, bn.bankName, c.countryISO2, b.isHeadquarter, b.swiftCode
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
