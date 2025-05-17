package com.example.task.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;

import com.example.task.repository.*;
import com.example.task.model.*;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvImportService {

    private final AddressRepository addressRepository;
    private final BankNameRepository bankNameRepository;
    private final BankRepository bankRepository;
    private final CodeTypeRepository codeTypeRepository;
    private final CountryRepository countryRepository;

    public void importFromCsv() throws Exception {
        try (InputStream inputStream = new ClassPathResource("swift_codes.csv").getInputStream();
             InputStreamReader streamReader = new InputStreamReader(inputStream);
             CSVReader reader = new CSVReader(streamReader)) {

            String[] nextLine;
            reader.readNext();

            List<Country> countries = countryRepository.findAll();
            List<BankName> bankNames = bankNameRepository.findAll();
            List<Address> addresses = addressRepository.findAll();
            List<CodeType> codeTypes = codeTypeRepository.findAll();
            List<Bank> existingBanks;

            while ((nextLine = reader.readNext()) != null) {

                existingBanks = bankRepository.findAllWithJoins();

                String dataCountryIso2 = nextLine[0];
                String dataSwiftCode = nextLine[1];
                String dataCodeType = nextLine[2];
                String dataBankName = nextLine[3];
                String dataAddress = nextLine[4];
                String dataTownName = nextLine[5];
                String dataCountryName = nextLine[6];
                String dataTimeZone = nextLine[7];

                boolean isHeadquarter = dataSwiftCode.endsWith("XXX");

                Country country = countries.stream()
                        .filter(c -> c.getCountryISO2().equalsIgnoreCase(dataCountryIso2))
                        .findFirst()
                        .orElseGet(() -> {
                            Country newCountry = new Country();
                            newCountry.setCountryISO2(dataCountryIso2);
                            newCountry.setCountryName(dataCountryName);
                            Country saved = countryRepository.save(newCountry);
                            countries.add(saved);
                            return saved;
                        });

                BankName bankName = bankNames.stream()
                        .filter(bn -> bn.getBankName().equalsIgnoreCase(dataBankName))
                        .findFirst()
                        .orElseGet(() -> {
                            BankName newBankName = new BankName();
                            newBankName.setBankName(dataBankName);
                            BankName saved = bankNameRepository.save(newBankName);
                            bankNames.add(saved);
                            return saved;
                        });

                Address address = addresses.stream()
                        .filter(a ->
                                a.getAddress().equalsIgnoreCase(dataAddress) &&
                                        a.getTownName().equalsIgnoreCase(dataTownName) &&
                                        a.getTimeZone().equalsIgnoreCase(dataTimeZone)
                        )
                        .findFirst()
                        .orElseGet(() -> {
                            Address newAddress = new Address();
                            newAddress.setAddress(dataAddress);
                            newAddress.setTownName(dataTownName);
                            newAddress.setTimeZone(dataTimeZone);
                            Address saved = addressRepository.save(newAddress);
                            addresses.add(saved);
                            return saved;
                        });

                CodeType codeType = codeTypes.stream()
                        .filter(ct -> ct.getCodeType().equalsIgnoreCase(dataCodeType))
                        .findFirst()
                        .orElseGet(() -> {
                            CodeType newCodeType = new CodeType();
                            newCodeType.setCodeType(dataCodeType);
                            CodeType saved = codeTypeRepository.save(newCodeType);
                            codeTypes.add(saved);
                            return saved;
                        });

                Bank bank = new Bank();
                bank.setSwiftCode(dataSwiftCode);
                bank.setHeadquarter(isHeadquarter);
                bank.setAddress(address);
                bank.setBankName(bankName);
                bank.setCountry(country);
                bank.setCodeType(codeType);


                if (!isHeadquarter) {
                    for (Bank hq : existingBanks) {
                        if (hq.isHeadquarter()) {
                            String hqSwift = hq.getSwiftCode();
                            if (hqSwift != null && hqSwift.length() >= 8 &&
                                    dataSwiftCode.length() >= 8 &&
                                    hqSwift.substring(0, 8).equals(dataSwiftCode.substring(0, 8))) {

                                bank.setHeadquarterBank(hq);
                                break;
                            }
                        }
                    }
                }

                Bank savedBank = bankRepository.save(bank);
                existingBanks.add(savedBank);
            }
        }
    }
}