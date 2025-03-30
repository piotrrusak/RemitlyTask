package com.example.task;

import com.example.task.entity.*;
import com.example.task.dao.BankRepository;
import com.example.task.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankServiceUnitTest {

    private BankService bankService;
    private BankRepository bankRepository;
    private BankNameService bankNameService;
    private CountryService countryService;
    private AddressService addressService;

    @BeforeEach
    void setup() throws Exception {

        bankRepository = mock(BankRepository.class);
        bankNameService = mock(BankNameService.class);
        countryService = mock(CountryService.class);
        addressService = mock(AddressService.class);

        bankService = new BankService(bankRepository);

        injectField("bankNameService", bankNameService);
        injectField("countryService", countryService);
        injectField("addressService", addressService);
    }

    void injectField(String fieldName, Object mock) throws Exception {
        Field field = BankService.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(bankService, mock);
    }

    @Test
    void shouldCreateBankFromValidDTO() {

        ThirdEndpointDTO dto = new ThirdEndpointDTO(
                "123 Main St", "BankName", "US", "United States", true, "BOFAUS3N"
        );

        when(bankRepository.findBySwiftCode("BOFAUS3N")).thenReturn(null);

        BankName bankName = new BankName();
        bankName.setBankName("BankName");
        when(bankNameService.findByBankName("BankName")).thenReturn(null);
        when(bankNameService.save(any())).thenReturn(bankName);

        Country country = new Country();
        country.setCountryISO2("US");
        country.setCountryName("United States");
        when(countryService.findByCountryName("United States")).thenReturn(null);
        when(countryService.save(any())).thenReturn(country);

        Address address = new Address();
        address.setAddress("123 Main St");
        when(addressService.findByAddress("123 Main St")).thenReturn(null);
        when(addressService.save(any())).thenReturn(address);

        Bank savedBank = new Bank();
        savedBank.setSwiftCode("BOFAUS3N");
        when(bankRepository.save(any())).thenReturn(savedBank);

        Bank result = bankService.createBankFromDTO(dto);

        assertNotNull(result);
        assertEquals("BOFAUS3N", result.getSwiftCode());
    }
}
