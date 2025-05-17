package com.example.task.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name = "bank_name_id")
    private BankName bankName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "swift_code_type_id")
    private CodeType codeType;

    @Column(name = "swift_code")
    private String swiftCode;

    @Column(name = "headquarter")
    private boolean headquarter;

    @ManyToOne
    @JoinColumn(name = "headquarterBank")
    private Bank headquarterBank;

}
