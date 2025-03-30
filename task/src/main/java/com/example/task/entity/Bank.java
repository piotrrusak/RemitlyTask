package com.example.task.entity;

import jakarta.persistence.*;

@Entity
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

    @Column(name = "is_headquarter")
    private boolean isHeadquarter;

    @ManyToOne
    @JoinColumn(name = "headquarter")
    private Bank headquarter;

    // === GETTERY I SETTERY ===

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BankName getBankName() {
        return bankName;
    }

    public void setBankName(BankName bankName) {
        this.bankName = bankName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public void setCodeType(CodeType codeType) {
        this.codeType = codeType;
    }

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

    public boolean isHeadquarter() {
        return isHeadquarter;
    }

    public void setHeadquarter(boolean headquarter) {
        isHeadquarter = headquarter;
    }

    public Bank getHeadquarter() {
        return headquarter;
    }

    public void setHeadquarter(Bank headquarter) {
        this.headquarter = headquarter;
    }
}
